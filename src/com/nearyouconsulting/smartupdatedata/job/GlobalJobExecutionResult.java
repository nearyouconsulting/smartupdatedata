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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.Configuration;


/**
 * @author aazri
 *
 */
public class GlobalJobExecutionResult
{
	private final Configuration configuration;
	private final List<JobSyncExecutionResult> jobSyncExecutionResults = new ArrayList<>();

	public GlobalJobExecutionResult(final Configuration configuration)
	{
		this.configuration = configuration;
	}

	public void add(final JobSyncExecutionResult jobSyncExecutionResult)
	{
		jobSyncExecutionResults.add(jobSyncExecutionResult);
	}


	/**
	 * @return the configuration
	 */
	public Configuration getConfiguration()
	{
		return configuration;
	}

	/**
	 * @return the jobSyncExecutionResults
	 */
	public List<JobSyncExecutionResult> getJobSyncExecutionResults()
	{
		return jobSyncExecutionResults;
	}



}

