package com.b2b.service;

import java.util.List;
import java.util.Map;

public interface LoginService {

	// �α��� ��������
	int loginCheck(Map<String, Object> map) throws Exception;

	// ȸ�����Ե� ���¿���
	int joinCheck(Map<String, Object> map) throws Exception;
	
	// �α��γ�¥ ������Ʈ
	int loginUpdate(Map<String, Object> map) throws Exception;
	
	// �α��� ����Ƚ�� ������Ʈ
	int bfatUpdate(Map<String, Object> map) throws Exception;
}
