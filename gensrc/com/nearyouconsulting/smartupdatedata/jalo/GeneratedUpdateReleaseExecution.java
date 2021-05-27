/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at May 27, 2021 12:40:00 AM                    ---
 * ----------------------------------------------------------------
 */
package com.nearyouconsulting.smartupdatedata.jalo;

import com.nearyouconsulting.smartupdatedata.constants.SmartupdatedataConstants;
import com.nearyouconsulting.smartupdatedata.jalo.FileEntry;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.util.OneToManyHandler;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.nearyouconsulting.smartupdatedata.jalo.UpdateReleaseExecution UpdateReleaseExecution}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedUpdateReleaseExecution extends GenericItem
{
	/** Qualifier of the <code>UpdateReleaseExecution.release</code> attribute **/
	public static final String RELEASE = "release";
	/** Qualifier of the <code>UpdateReleaseExecution.revisionNumber</code> attribute **/
	public static final String REVISIONNUMBER = "revisionNumber";
	/** Qualifier of the <code>UpdateReleaseExecution.executionDate</code> attribute **/
	public static final String EXECUTIONDATE = "executionDate";
	/** Qualifier of the <code>UpdateReleaseExecution.executionUser</code> attribute **/
	public static final String EXECUTIONUSER = "executionUser";
	/** Qualifier of the <code>UpdateReleaseExecution.fileEntry</code> attribute **/
	public static final String FILEENTRY = "fileEntry";
	/**
	* {@link OneToManyHandler} for handling 1:n FILEENTRY's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<FileEntry> FILEENTRYHANDLER = new OneToManyHandler<FileEntry>(
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
		tmp.put(RELEASE, AttributeMode.INITIAL);
		tmp.put(REVISIONNUMBER, AttributeMode.INITIAL);
		tmp.put(EXECUTIONDATE, AttributeMode.INITIAL);
		tmp.put(EXECUTIONUSER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UpdateReleaseExecution.executionDate</code> attribute.
	 * @return the executionDate
	 */
	public Date getExecutionDate(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, EXECUTIONDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UpdateReleaseExecution.executionDate</code> attribute.
	 * @return the executionDate
	 */
	public Date getExecutionDate()
	{
		return getExecutionDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UpdateReleaseExecution.executionDate</code> attribute. 
	 * @param value the executionDate
	 */
	public void setExecutionDate(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, EXECUTIONDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UpdateReleaseExecution.executionDate</code> attribute. 
	 * @param value the executionDate
	 */
	public void setExecutionDate(final Date value)
	{
		setExecutionDate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UpdateReleaseExecution.executionUser</code> attribute.
	 * @return the executionUser
	 */
	public User getExecutionUser(final SessionContext ctx)
	{
		return (User)getProperty( ctx, EXECUTIONUSER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UpdateReleaseExecution.executionUser</code> attribute.
	 * @return the executionUser
	 */
	public User getExecutionUser()
	{
		return getExecutionUser( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UpdateReleaseExecution.executionUser</code> attribute. 
	 * @param value the executionUser
	 */
	public void setExecutionUser(final SessionContext ctx, final User value)
	{
		setProperty(ctx, EXECUTIONUSER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UpdateReleaseExecution.executionUser</code> attribute. 
	 * @param value the executionUser
	 */
	public void setExecutionUser(final User value)
	{
		setExecutionUser( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UpdateReleaseExecution.fileEntry</code> attribute.
	 * @return the fileEntry
	 */
	public List<FileEntry> getFileEntry(final SessionContext ctx)
	{
		return (List<FileEntry>)FILEENTRYHANDLER.getValues( ctx, this );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UpdateReleaseExecution.fileEntry</code> attribute.
	 * @return the fileEntry
	 */
	public List<FileEntry> getFileEntry()
	{
		return getFileEntry( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UpdateReleaseExecution.fileEntry</code> attribute. 
	 * @param value the fileEntry
	 */
	public void setFileEntry(final SessionContext ctx, final List<FileEntry> value)
	{
		FILEENTRYHANDLER.setValues( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UpdateReleaseExecution.fileEntry</code> attribute. 
	 * @param value the fileEntry
	 */
	public void setFileEntry(final List<FileEntry> value)
	{
		setFileEntry( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to fileEntry. 
	 * @param value the item to add to fileEntry
	 */
	public void addToFileEntry(final SessionContext ctx, final FileEntry value)
	{
		FILEENTRYHANDLER.addValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to fileEntry. 
	 * @param value the item to add to fileEntry
	 */
	public void addToFileEntry(final FileEntry value)
	{
		addToFileEntry( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from fileEntry. 
	 * @param value the item to remove from fileEntry
	 */
	public void removeFromFileEntry(final SessionContext ctx, final FileEntry value)
	{
		FILEENTRYHANDLER.removeValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from fileEntry. 
	 * @param value the item to remove from fileEntry
	 */
	public void removeFromFileEntry(final FileEntry value)
	{
		removeFromFileEntry( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UpdateReleaseExecution.release</code> attribute.
	 * @return the release
	 */
	public String getRelease(final SessionContext ctx)
	{
		return (String)getProperty( ctx, RELEASE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UpdateReleaseExecution.release</code> attribute.
	 * @return the release
	 */
	public String getRelease()
	{
		return getRelease( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UpdateReleaseExecution.release</code> attribute. 
	 * @param value the release
	 */
	public void setRelease(final SessionContext ctx, final String value)
	{
		setProperty(ctx, RELEASE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UpdateReleaseExecution.release</code> attribute. 
	 * @param value the release
	 */
	public void setRelease(final String value)
	{
		setRelease( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UpdateReleaseExecution.revisionNumber</code> attribute.
	 * @return the revisionNumber
	 */
	public String getRevisionNumber(final SessionContext ctx)
	{
		return (String)getProperty( ctx, REVISIONNUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>UpdateReleaseExecution.revisionNumber</code> attribute.
	 * @return the revisionNumber
	 */
	public String getRevisionNumber()
	{
		return getRevisionNumber( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UpdateReleaseExecution.revisionNumber</code> attribute. 
	 * @param value the revisionNumber
	 */
	public void setRevisionNumber(final SessionContext ctx, final String value)
	{
		setProperty(ctx, REVISIONNUMBER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>UpdateReleaseExecution.revisionNumber</code> attribute. 
	 * @param value the revisionNumber
	 */
	public void setRevisionNumber(final String value)
	{
		setRevisionNumber( getSession().getSessionContext(), value );
	}
	
}
