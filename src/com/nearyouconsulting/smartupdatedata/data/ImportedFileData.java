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
package com.nearyouconsulting.smartupdatedata.data;

import java.io.File;
import java.util.List;

import com.nearyouconsulting.smartupdatedata.enums.StatusFileEnum;


public class ImportedFileData
{
	private File file;
	private StatusFileEnum status;
	private String errorMessage;
	private List<ImportedFileData> importedImpexData;

	/**
	 * @return the file
	 */
	public File getFile()
	{
		return file;
	}

	/**
	 * @param file
	 *           the file to set
	 */
	public void setFile(final File file)
	{
		this.file = file;
	}


	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage()
	{
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *           the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the importedImpexData
	 */
	public List<ImportedFileData> getImportedFileData()
	{
		return importedImpexData;
	}

	/**
	 * @param importedImpexData
	 *           the importedImpexData to set
	 */
	public void setImportedFileData(final List<ImportedFileData> importedImpexData)
	{
		this.importedImpexData = importedImpexData;
	}

	/**
	 * @return the status
	 */
	public StatusFileEnum getStatus()
	{
		return status;
	}

	/**
	 * @param status
	 *           the status to set
	 */
	public void setStatus(final StatusFileEnum status)
	{
		this.status = status;
	}

}
