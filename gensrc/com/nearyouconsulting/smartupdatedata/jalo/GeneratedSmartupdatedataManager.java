/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at May 27, 2021 12:40:00 AM                    ---
 * ----------------------------------------------------------------
 */
package com.nearyouconsulting.smartupdatedata.jalo;

import com.nearyouconsulting.smartupdatedata.constants.SmartupdatedataConstants;
import com.nearyouconsulting.smartupdatedata.jalo.FileEntry;
import com.nearyouconsulting.smartupdatedata.jalo.UpdateReleaseExecution;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>SmartupdatedataManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSmartupdatedataManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	public FileEntry createFileEntry(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( SmartupdatedataConstants.TC.FILEENTRY );
			return (FileEntry)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating FileEntry : "+e.getMessage(), 0 );
		}
	}
	
	public FileEntry createFileEntry(final Map attributeValues)
	{
		return createFileEntry( getSession().getSessionContext(), attributeValues );
	}
	
	public UpdateReleaseExecution createUpdateReleaseExecution(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( SmartupdatedataConstants.TC.UPDATERELEASEEXECUTION );
			return (UpdateReleaseExecution)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating UpdateReleaseExecution : "+e.getMessage(), 0 );
		}
	}
	
	public UpdateReleaseExecution createUpdateReleaseExecution(final Map attributeValues)
	{
		return createUpdateReleaseExecution( getSession().getSessionContext(), attributeValues );
	}
	
	@Override
	public String getName()
	{
		return SmartupdatedataConstants.EXTENSIONNAME;
	}
	
}
