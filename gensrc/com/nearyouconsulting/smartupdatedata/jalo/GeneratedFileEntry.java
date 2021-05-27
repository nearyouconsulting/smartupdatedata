/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at May 27, 2021 12:40:00 AM                    ---
 * ----------------------------------------------------------------
 */
package com.nearyouconsulting.smartupdatedata.jalo;

import com.nearyouconsulting.smartupdatedata.constants.SmartupdatedataConstants;
import com.nearyouconsulting.smartupdatedata.jalo.UpdateReleaseExecution;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.media.Media;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.nearyouconsulting.smartupdatedata.jalo.FileEntry FileEntry}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedFileEntry extends GenericItem
{
	/** Qualifier of the <code>FileEntry.file</code> attribute **/
	public static final String FILE = "file";
	/** Qualifier of the <code>FileEntry.status</code> attribute **/
	public static final String STATUS = "status";
	/** Qualifier of the <code>FileEntry.errorMessage</code> attribute **/
	public static final String ERRORMESSAGE = "errorMessage";
	/** Qualifier of the <code>FileEntry.updateReleaseExecution</code> attribute **/
	public static final String UPDATERELEASEEXECUTION = "updateReleaseExecution";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n UPDATERELEASEEXECUTION's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedFileEntry> UPDATERELEASEEXECUTIONHANDLER = new BidirectionalOneToManyHandler<GeneratedFileEntry>(
	SmartupdatedataConstants.TC.FILEENTRY,
	false,
	"updateReleaseExecution",
	null,
	false,
	true,
	CollectionType.LIST
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(FILE, AttributeMode.INITIAL);
		tmp.put(STATUS, AttributeMode.INITIAL);
		tmp.put(ERRORMESSAGE, AttributeMode.INITIAL);
		tmp.put(UPDATERELEASEEXECUTION, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		UPDATERELEASEEXECUTIONHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FileEntry.errorMessage</code> attribute.
	 * @return the errorMessage
	 */
	public String getErrorMessage(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ERRORMESSAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FileEntry.errorMessage</code> attribute.
	 * @return the errorMessage
	 */
	public String getErrorMessage()
	{
		return getErrorMessage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FileEntry.errorMessage</code> attribute. 
	 * @param value the errorMessage
	 */
	public void setErrorMessage(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ERRORMESSAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FileEntry.errorMessage</code> attribute. 
	 * @param value the errorMessage
	 */
	public void setErrorMessage(final String value)
	{
		setErrorMessage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FileEntry.file</code> attribute.
	 * @return the file
	 */
	public Media getFile(final SessionContext ctx)
	{
		return (Media)getProperty( ctx, FILE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FileEntry.file</code> attribute.
	 * @return the file
	 */
	public Media getFile()
	{
		return getFile( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FileEntry.file</code> attribute. 
	 * @param value the file
	 */
	public void setFile(final SessionContext ctx, final Media value)
	{
		setProperty(ctx, FILE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FileEntry.file</code> attribute. 
	 * @param value the file
	 */
	public void setFile(final Media value)
	{
		setFile( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FileEntry.status</code> attribute.
	 * @return the status
	 */
	public EnumerationValue getStatus(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, STATUS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FileEntry.status</code> attribute.
	 * @return the status
	 */
	public EnumerationValue getStatus()
	{
		return getStatus( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FileEntry.status</code> attribute. 
	 * @param value the status
	 */
	public void setStatus(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, STATUS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FileEntry.status</code> attribute. 
	 * @param value the status
	 */
	public void setStatus(final EnumerationValue value)
	{
		setStatus( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FileEntry.updateReleaseExecution</code> attribute.
	 * @return the updateReleaseExecution
	 */
	public UpdateReleaseExecution getUpdateReleaseExecution(final SessionContext ctx)
	{
		return (UpdateReleaseExecution)getProperty( ctx, UPDATERELEASEEXECUTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FileEntry.updateReleaseExecution</code> attribute.
	 * @return the updateReleaseExecution
	 */
	public UpdateReleaseExecution getUpdateReleaseExecution()
	{
		return getUpdateReleaseExecution( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FileEntry.updateReleaseExecution</code> attribute. 
	 * @param value the updateReleaseExecution
	 */
	public void setUpdateReleaseExecution(final SessionContext ctx, final UpdateReleaseExecution value)
	{
		UPDATERELEASEEXECUTIONHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FileEntry.updateReleaseExecution</code> attribute. 
	 * @param value the updateReleaseExecution
	 */
	public void setUpdateReleaseExecution(final UpdateReleaseExecution value)
	{
		setUpdateReleaseExecution( getSession().getSessionContext(), value );
	}
	
}
