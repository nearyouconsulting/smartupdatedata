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
package com.nearyouconsulting.smartupdatedata.job;

import de.hybris.platform.servicelayer.cronjob.PerformResult;


/**
 * @author aazri
 *
 */
public class JobSyncExecutionResult extends JobExecutionResult
{
	private final String catalogId;


	public JobSyncExecutionResult(final String catalogId, final PerformResult result)
	{
		super(result);
		this.catalogId = catalogId;
	}


	@Override
	public String toString()
	{
		return String.format("{catalogId : \"%s\",result : \"%s\", status : \"%s\"}", catalogId, getPerformResult().getResult(),
				getPerformResult().getStatus());
	}


	/**
	 * @return the catalogId
	 */
	public String getCatalogId()
	{
		return catalogId;
	}


}
