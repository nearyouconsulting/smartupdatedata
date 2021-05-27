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
package com.nearyouconsulting.smartupdatedata.service;

import java.util.List;

import com.nearyouconsulting.smartupdatedata.data.ImportedFileData;



/**
 * @author aazri
 */
public interface ReleaseExecutionHistoryService
{
	public void saveUpdateReleaseExecution(final List<ImportedFileData> importedFiles, String release);
}