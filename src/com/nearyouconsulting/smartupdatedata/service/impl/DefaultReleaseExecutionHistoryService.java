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

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.media.MediaIOException;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.nearyouconsulting.smartupdatedata.constants.SmartupdatedataConstants;
import com.nearyouconsulting.smartupdatedata.data.ImportedFileData;
import com.nearyouconsulting.smartupdatedata.model.FileEntryModel;
import com.nearyouconsulting.smartupdatedata.model.UpdateReleaseExecutionModel;
import com.nearyouconsulting.smartupdatedata.service.ReleaseExecutionHistoryService;


/**
 * @author aazri
 */
public class DefaultReleaseExecutionHistoryService implements ReleaseExecutionHistoryService
{
	private static final Logger LOG = Logger.getLogger(DefaultReleaseExecutionHistoryService.class);

	private ModelService modelService;
	private MediaService mediaService;
	private CatalogVersionService catalogVersionService;
	private UserService userService;

	@Override
	public void saveUpdateReleaseExecution(final List<ImportedFileData> importedFiles, final String release)
	{
		final UpdateReleaseExecutionModel updateReleaseExecutionModel = modelService.create(UpdateReleaseExecutionModel.class);
		updateReleaseExecutionModel.setExecutionDate(new Date());
		updateReleaseExecutionModel.setRelease(release);

		for (final ImportedFileData importedFileData : importedFiles)
		{
			final FileEntryModel fileEntryModel = modelService.create(FileEntryModel.class);
			try
			{
				fileEntryModel.setFile(createMedia(importedFileData.getFile()));
			}
			catch (MediaIOException | IllegalArgumentException | FileNotFoundException e)
			{
				LOG.error("Resource: " + importedFileData.getFile() + " was not persisted");
			}
			//fileEntryModel.setStatus(importedFileData.getStatus());
			fileEntryModel.setErrorMessage(importedFileData.getErrorMessage());
			fileEntryModel.setUpdateReleaseExecution(updateReleaseExecutionModel);
			modelService.save(fileEntryModel);
		}
	}

	private MediaModel createMedia(final File file) throws MediaIOException, IllegalArgumentException, FileNotFoundException
	{

		final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion(SmartupdatedataConstants.CONTENT_CATALOG,
				SmartupdatedataConstants.VERSION);

		MediaModel mediaModel;

		try
		{
			mediaModel = mediaService.getMedia(catalogVersion, file.getName());
		}
		catch (final UnknownIdentifierException e)
		{
			mediaModel = modelService.create(MediaModel.class);
		}

		mediaModel.setCode(file.getName());
		mediaModel.setCatalogVersion(catalogVersion);
		mediaModel.setMime("text/csv");
		mediaModel.setRealFileName(file.getName());
		modelService.save(mediaModel);
		mediaService.setStreamForMedia(mediaModel, new FileInputStream(file));

		return mediaModel;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the mediaService
	 */
	public MediaService getMediaService()
	{
		return mediaService;
	}

	/**
	 * @param mediaService
	 *           the mediaService to set
	 */
	public void setMediaService(final MediaService mediaService)
	{
		this.mediaService = mediaService;
	}

	/**
	 * @return the catalogVersionService
	 */
	public CatalogVersionService getCatalogVersionService()
	{
		return catalogVersionService;
	}

	/**
	 * @param catalogVersionService
	 *           the catalogVersionService to set
	 */
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService
	 *           the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

}
