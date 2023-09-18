package com.gq.portlet;

import com.gq.constants.InstallModulesPortletKeys;
import com.liferay.marketplace.exception.FileExtensionException;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadException;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.io.File;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(
		 property = {
			 "javax.portlet.name="+ InstallModulesPortletKeys.INSTALLMODULES,
			 "mvc.command.name=/installLocalApp"
		 },
		 service = MVCActionCommand.class
	 )

public class InstallLocalAppMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		UploadPortletRequest uploadPortletRequest =
				_portal.getUploadPortletRequest(actionRequest);

			String fileName = GetterUtil.getString(
				uploadPortletRequest.getFileName("file"));

			File file = uploadPortletRequest.getFile("file");

			if (ArrayUtil.isEmpty(FileUtil.getBytes(file))) {
				SessionErrors.add(actionRequest, UploadException.class.getName());
			}
			else if (!fileName.endsWith(".jar") && !fileName.endsWith(".lpkg") &&
					 !fileName.endsWith(".war")) {

				throw new FileExtensionException();
			}
			else {
				String deployDir = PropsUtil.get(PropsKeys.AUTO_DEPLOY_DEPLOY_DIR);

				FileUtil.copyFile(
					file.toString(), deployDir + StringPool.SLASH + fileName);

				SessionMessages.add(actionRequest, "pluginUploaded");
			}		
	}
	
	@Reference
	private Portal _portal;

}
