/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.nearyouconsulting.smartupdatedata.setup;

import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.impex.ImportConfig;
import de.hybris.platform.servicelayer.impex.ImportResult;
import de.hybris.platform.servicelayer.impex.ImportService;
import de.hybris.platform.servicelayer.impex.impl.ClasspathImpExResource;
import de.hybris.platform.servicelayer.media.MediaIOException;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.JspContext;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.codehaus.groovy.control.CompilationFailedException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.nearyouconsulting.smartupdatedata.constants.SmartupdatedataConstants;
import com.nearyouconsulting.smartupdatedata.data.ImportedFileData;
import com.nearyouconsulting.smartupdatedata.enums.StatusFileEnum;
import com.nearyouconsulting.smartupdatedata.service.ReleaseExecutionHistoryService;
import com.nearyouconsulting.smartupdatedata.service.SetupCatalogSyncJobService;
import com.nearyouconsulting.smartupdatedata.service.SetupRunSolrJobService;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.MissingPropertyException;


@SystemSetup(extension = SmartupdatedataConstants.EXTENSIONNAME)
public class SmartupdatedataSystemSetup extends AbstractSystemSetup
{
	private static final Logger LOG = Logger.getLogger(SmartupdatedataSystemSetup.class);
	private static final String IMPEX_FOLDER = "/impex";
	private static final String GROOVY_FOLDER = "/groovy";
	private static final String ACTIVATE_SOLR_CRON_JOBS = "activateSolrCronJobs";
	private static final String IMPORT_CORE_DATA = "importCoreData";

	List<ImportedFileData> importedFiles;
	private ReleaseExecutionHistoryService releaseExecutionHistoryService;
	private SetupCatalogSyncJobService setupCatalogSyncJobService;
	private ImportService importService;
	private BaseSiteService baseSiteService;
	private SetupRunSolrJobService setupRunSolrJobService;

	@Override
	@SystemSetupParameterMethod
	public List<SystemSetupParameter> getInitializationOptions()
	{
		final List<SystemSetupParameter> params = new ArrayList<SystemSetupParameter>();
		final String ressoureDir = getImpexResourcesDir();
		getAllFolders(params, ressoureDir);
		params.add(createBooleanSystemSetupParameter(ACTIVATE_SOLR_CRON_JOBS, "Activate Solr Cron Jobs", true));

		return params;
	}


	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		//
	}


	@SystemSetup(type = Type.PROJECT, process = Process.UPDATE)
	public void createProjectData(final SystemSetupContext context)
	{
		final JspContext jspc = context.getJspContext();
		final String resourceDirRevision = getImpexResourcesDir();
		importedFiles = new ArrayList<>();
		final File[] folders = getContentOfRessource(resourceDirRevision, DirectoryFileFilter.INSTANCE);
		if (ArrayUtils.isNotEmpty(folders))
		{
			for (final File folder : folders)
			{
				LOG.debug("folder is : " + folder.getName());
				if (getBooleanSystemSetupParameter(context, folder.getName()))
				{
					try
					{
						final String folderPath = resourceDirRevision + "/" + folder.getName();
						jspc.println(
								"<b><font color='black'>Starting Importing impex of release : " + folder.getName() + "</font></b>");
						importDatasImpex(jspc, folderPath, folder.getName());
						importDatasGroovy(jspc, folderPath, folder.getName());

					}
					catch (final Exception e)
					{
						LOG.error(e.getMessage(), e);
					}
					jspc.println("<b><font color='black'>End Importing impex of release : " + folder.getName() + "</font></b>");
				}
			}
		}

		// Call synchronization jobs
		setupCatalogSyncJobService.runAllCatalogSyncJobs(jspc);
		LOG.info("End synchronization of all catalogs!");
		jspc.println("<b><font color='orange'>End synchronization of all catalogs! </font></b>");
		//		final List<String> storeNmes = new ArrayListWrapper(Config.getParameter("smartupdatedata.solr.storeNames").split(","));

		//		for (final String storeName : storeNmes)
		//		{
		//			if (getBooleanSystemSetupParameter(context, ACTIVATE_SOLR_CRON_JOBS))
		//			{
		//				logInfo(context, String.format("Activating solr index for [%s]", storeName));
		//				setupRunSolrJobService.runSolrIndex(storeName);
		//			}
		//		}
		// Call indexing jobs

	}

	/**
	 * This method import all data from Impex files
	 *
	 * @param revisionDir
	 * @param release
	 *           YTODO
	 */
	private void importDatasImpex(final JspContext jspc, final String revisionDir, final String release)
			throws UnsupportedEncodingException, ImpExException
	{

		StringBuffer folderPath = new StringBuffer(revisionDir);
		folderPath = folderPath.append(IMPEX_FOLDER);

		final File[] files = getContentOfRessource(folderPath.toString(), new WildcardFileFilter("*.impex"));
		if (ArrayUtils.isNotEmpty(files))
		{
			for (final File file : files)
			{
				final String filePath = folderPath + "/" + file.getName();
				importImpex(filePath, jspc, file);

			}
		}

		if (Config.getBoolean("smartupdatedata.release.traceability.activate", false))
		{
			releaseExecutionHistoryService.saveUpdateReleaseExecution(importedFiles, release);
			//emailRelease.sendEmail(importedFiles);
		}


	}

	/**
	 * This method execute an impex using a cronjob
	 *
	 * @param impex
	 *           the name of the impex file to be imported
	 * @param jspc
	 *
	 * @param file
	 */
	@SuppressWarnings("deprecation")
	private void importImpex(final String impex, final JspContext jspc, final File file)
			throws UnsupportedEncodingException, ImpExException
	{
		StatusFileEnum status = null;
		final ImportConfig config = new ImportConfig();
		final ClasspathImpExResource impExResource = new ClasspathImpExResource(impex, "UTF-8");
		config.setScript(impExResource);
		config.setSynchronous(true);
		config.setMaxThreads(16);
		config.setFailOnError(true);

		LOG.info("Importing resource: " + impex);
		// when
		final String message = "Importing [" + file + "]...";
		try
		{
			final ImportResult result = importService.importData(config);
			if (result.isSuccessful())
			{
				jspc.println("<font color='green'>Importing " + impex + " | OK |</font>");

				status = StatusFileEnum.SUCCESS;
				LOG.info(status.getCode());

			}
			else
			{
				jspc.println("<font color='red'>Importing " + impex + " | KO</font>");
				status = StatusFileEnum.ERROR;
				LOG.error("Resource: " + impex + " was not imported, status = " + result.isError());
			}
		}
		catch (final Exception e)
		{
			LOG.error(message + " FAILED", e);
		}

		addFileEntryForTraceability(file, status);
	}

	/**
	 *
	 *
	 * @throws IllegalArgumentException
	 * @throws MediaIOException
	 */
	private void addFileEntryForTraceability(final File impex, final StatusFileEnum status)
	{
		final ImportedFileData impexData = new ImportedFileData();
		impexData.setFile(impex);
		impexData.setStatus(status);
		importedFiles.add(impexData);

	}

	private void importDatasGroovy(final JspContext jspc, final String revisionDir, final String release)
			throws UnsupportedEncodingException, ImpExException
	{

		StringBuffer folderPath = new StringBuffer(revisionDir);
		folderPath = folderPath.append(GROOVY_FOLDER);

		final File[] files = getContentOfRessource(folderPath.toString(), new WildcardFileFilter("*.groovy"));
		if (ArrayUtils.isNotEmpty(files))
		{
			for (final File file : files)
			{
				final String filePath = folderPath + "/" + file.getName();
				importGroovy(filePath, jspc, file);

			}
		}


		if (Config.getBoolean("updatedata.release.traceability.file.activate", false))
		{
			releaseExecutionHistoryService.saveUpdateReleaseExecution(importedFiles, release);
			//emailRelease.sendEmail(importedFiles);
		}


	}


	/**
	 * @param filePath
	 * @param jspc
	 * @param file
	 */
	private void importGroovy(final String filePath, final JspContext jspc, final File file)
	{
		StatusFileEnum status = null;
		LOG.info("Executing script : " + filePath);
		try
		{
			evaluateScript(file);
			status = StatusFileEnum.SUCCESS;
			jspc.println("<font color='green'>Executing " + filePath + " - OK </font>");
		}
		catch (CompilationFailedException | IOException e)
		{
			status = StatusFileEnum.ERROR;
			jspc.println("<font color='red'>Executing " + filePath + " - KO </font>");
			LOG.error("error when executing the script" + filePath, e);
		}

		addFileEntryForTraceability(file, status);

	}


	private void evaluateScript(final File file) throws CompilationFailedException, IOException
	{
		final ApplicationContext springCtx = Registry.getApplicationContext();
		final Map<String, Object> ctx = new HashMap<String, Object>();
		ctx.put("ctx", Registry.getApplicationContext());
		final Binding binding = new Binding(ctx)
		{
			@Override
			public Object getVariable(final String name)
			{
				try
				{
					return super.getVariable(name);
				}
				catch (final MissingPropertyException e)
				{
					try
					{
						return springCtx.getBean(name);
					}
					catch (final BeansException localBeansException)
					{
						throw e;
					}
				}
			}
		};
		final GroovyShell interpreter = new GroovyShell(binding);
		interpreter.evaluate(file);
	}

	/**
	 * @param params
	 * @param ressoureDir
	 */
	private void getAllFolders(final List<SystemSetupParameter> params, final String ressoureDir)
	{
		final File[] folders = getContentOfRessource(ressoureDir, DirectoryFileFilter.INSTANCE);

		if (ArrayUtils.isNotEmpty(folders))
		{
			for (final File folder : folders)
			{
				final String folderName = folder.getName();
				params.add(createBooleanSystemSetupParameter(folderName, folderName, false));
			}
		}
	}

	/**
	 * This method take the path of directory and return it content according to the filter {@code fileFilter}
	 *
	 * @param ressoureDir
	 *           the path of source directory
	 * @param fileFilter
	 *           - filter
	 * @return content of the folder
	 */
	private File[] getContentOfRessource(final String ressoureDir, final FileFilter fileFilter)
	{
		final URL url = this.getClass().getResource(ressoureDir);
		if (url != null)
		{
			final File file = new File(url.getFile());
			final File[] files = file.listFiles(fileFilter);
			java.util.Arrays.sort(files);
			return files;
		}
		else
		{
			LOG.error("No file or folder found on " + ressoureDir);
			return null;
		}
	}



	/**
	 * @return the path of the impex directory
	 */
	private static String getImpexResourcesDir()
	{
		return Config.getParameter("smartupdatedata.data.resources.dir");
	}


	/**
	 * @return the setupCatalogSyncJobService
	 */
	public SetupCatalogSyncJobService getSetupCatalogSyncJobService()
	{
		return setupCatalogSyncJobService;
	}


	/**
	 * @param setupCatalogSyncJobService
	 *           the setupCatalogSyncJobService to set
	 */
	public void setSetupCatalogSyncJobService(final SetupCatalogSyncJobService setupCatalogSyncJobService)
	{
		this.setupCatalogSyncJobService = setupCatalogSyncJobService;
	}


	/**
	 * @return the importService
	 */
	public ImportService getImportService()
	{
		return importService;
	}


	/**
	 * @param importService
	 *           the importService to set
	 */
	public void setImportService(final ImportService importService)
	{
		this.importService = importService;
	}


	public ReleaseExecutionHistoryService getReleaseExecutionHistoryService()
	{
		return releaseExecutionHistoryService;
	}


	public void setReleaseExecutionHistoryService(final ReleaseExecutionHistoryService releaseExecutionHistoryService)
	{
		this.releaseExecutionHistoryService = releaseExecutionHistoryService;
	}


}
