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

import de.hybris.platform.commerceservices.setup.SetupSolrIndexerService;

import com.nearyouconsulting.smartupdatedata.service.SetupRunSolrJobService;


/**
 * @author machd
 */
public class DefaultSetupRunSolrJobService implements SetupRunSolrJobService
{
	private SetupSolrIndexerService setupSolrIndexerService;

	/**
	 * this method will synchronizing solr.
	 *
	 * @param storeName
	 *           which store should be run solr.
	 */
	@Override
	public void runSolrIndex(final String storeName)
	{
		getSetupSolrIndexerService().executeSolrIndexerCronJob(String.format("%sIndex", storeName), true);
		getSetupSolrIndexerService().activateSolrIndexerCronJobs(String.format("%sIndex", storeName));
	}

	public SetupSolrIndexerService getSetupSolrIndexerService()
	{
		return setupSolrIndexerService;
	}

	public void setSetupSolrIndexerService(final SetupSolrIndexerService setupSolrIndexerService)
	{
		this.setupSolrIndexerService = setupSolrIndexerService;
	}

}
