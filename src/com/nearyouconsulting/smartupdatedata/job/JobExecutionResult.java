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
public class JobExecutionResult
{
	private final PerformResult performResult;


	public JobExecutionResult(final PerformResult performResult)
	{
		this.performResult = performResult;
	}


	/**
	 * @return the performResult
	 */
	public PerformResult getPerformResult()
	{
		return performResult;
	}

}
