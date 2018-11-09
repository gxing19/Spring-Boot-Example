package com.springboot.email.common.util;

import org.springframework.core.io.FileSystemResource;

/**
 * 邮件实体类
 */
public class MailBean {

	/**
	 * 邮件主题
	 */
	private String subject;

	/**
	 * 邮件内容
	 */
	private String text;

	/**
	 * 附件
	 */
	private FileSystemResource file;

	/**
	 * 附件名称
	 */
	private String attachmentFilename;

	/**
	 * 内容ID，用于发送静态资源邮件时使用
	 */
	private String contentId;

	public static MailBean getMailBean() {
		return new MailBean();
	}

	public String getSubject() {
		return subject;
	}

	public MailBean setSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public String getText() {
		return text;
	}

	public MailBean setText(String text) {
		this.text = text;
		return this;
	}

	public FileSystemResource getFile() {
		return file;
	}

	public MailBean setFile(FileSystemResource file) {
		this.file = file;
		return this;
	}

	public String getAttachmentFilename() {
		return attachmentFilename;
	}

	public MailBean setAttachmentFilename(String attachmentFilename) {
		this.attachmentFilename = attachmentFilename;
		return this;
	}

	public String getContentId() {
		return contentId;
	}

	public MailBean setContentId(String contentId) {
		this.contentId = contentId;
		return this;
	}
}
