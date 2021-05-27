/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2021 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.nearyouconsulting.smartupdatedata.service.impl;

import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.commerceservices.setup.SetupSyncJobService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.util.JspContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import com.nearyouconsulting.smartupdatedata.job.GlobalJobExecutionResult;
import com.nearyouconsulting.smartupdatedata.job.JobSyncExecutionResult;
import com.nearyouconsulting.smartupdatedata.service.SetupCatalogSyncJobService;


public class DefaultSetupCatalogSyncJobService implements SetupCatalogSyncJobService
{
	private static final Logger LOG = Logger.getLogger(DefaultSetupCatalogSyncJobService.class);
	private CatalogService catalogService;
	private SetupSyncJobService setupSyncJobService;
	private ConfigurationService configurationService;

	@Override
	public GlobalJobExecutionResult runAllCatalogSyncJobs(final JspContext jspc)
	{
		LOG.info("Start synchronization of All catalogs : ");
		jspc.println("<b><font color='orange'>Start synchronization of All catalogs : </font></b>");
		final Collection<CatalogModel> allCatalogs = catalogService.getAllCatalogs();

		final List<String> allCatalogIds = new ArrayList<String>();

		for (final CatalogModel catalogModel : allCatalogs)
		{
			allCatalogIds.add(catalogModel.getId());
		}

		return callInternal(allCatalogIds, jspc);

	}

	private GlobalJobExecutionResult callInternal(final List<String> catalogs, final JspContext jspc)
	{
		final GlobalJobExecutionResult globalJobExecutionResult = new GlobalJobExecutionResult(
				configurationService.getConfiguration());
		for (final String singleCall : catalogs)
		{
			if (!singleCall.contains("Default") && !singleCall.contains("ERP_CLASSIFICATION"))
			{

				LOG.info("Start synchronization of catalog with  Id : " + singleCall + "</font></b>");

				jspc.println("<b><font color='orange'>Start synchronization of catalog with  Id : " + singleCall + "</font></b>");

				PerformResult result = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
				try
				{

					final PerformResult singleResult = setupSyncJobService.executeCatalogSyncJob(singleCall);


					LOG.info("End synchronization of catalog with  Id : " + singleCall);
					jspc.println("<b><font color='orange'>End synchronization of catalog with  Id : " + singleCall + "</font></b>");


					if (singleResult.getResult() != CronJobResult.SUCCESS)
					{
						result = singleResult;
					}
				}
				catch (final Exception e)
				{
					LOG.error("One of the cronjob computations caused an exception : " + e.getMessage());
					if (LOG.isDebugEnabled())
					{
						LOG.debug(e);
					}

					result = new PerformResult(CronJobResult.ERROR, CronJobStatus.ABORTED);
				}

				final JobSyncExecutionResult jobSyncExecutionResult = new JobSyncExecutionResult(singleCall, result);
				globalJobExecutionResult.add(jobSyncExecutionResult);
			}
		}

		return globalJobExecutionResult;
	}

	public CatalogService getCatalogService()
	{
		return catalogService;
	}

	public void setCatalogService(final CatalogService catalogService)
	{
		this.catalogService = catalogService;
	}

	public SetupSyncJobService getSetupSyncJobService()
	{
		return setupSyncJobService;
	}

	public void setSetupSyncJobService(final SetupSyncJobService setupSyncJobService)
	{
		this.setupSyncJobService = setupSyncJobService;
	}

	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}

}
