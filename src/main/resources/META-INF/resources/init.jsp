<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@page import="com.liferay.portal.kernel.service.CompanyLocalServiceUtil" %>
<%@page import="com.liferay.marketplace.exception.FileExtensionException" %>
<%@page import="com.liferay.portal.kernel.upload.UploadException" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />