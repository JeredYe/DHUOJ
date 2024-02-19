package edu.dhu.ws;

public interface OJWS {

	//����
	public String test(String message);
	// ����½�˵���Ϣת����xml��ʽ
	public String wsLogin(String userName, String userPassword);

	// �������б�ת����xml��ʽ
	public String wsGetExamList(String userName, String password);

	// ͨ��Id�������б�ת����xml��ʽ
	public String wsGetExamById(String userName, String password, int examId);

	// ����problemId���problems��problemtestcases�������Ϣת����xml��ʽ
	public byte[] wsGetProblem(String userName, String password,int examId, int problemId);

	// ����examId���examProblems�������Ϣת����xml��ʽ
	public String wsGetExamProblems(String userName, String password,
			int examId, int page, int rows);
	
	// �����û��������룬examId��ȡexamdetail�������Ϣת����xml��ʽ
	public String wsGetExamDetail(String userName, String password, int examId, int page, int rows);

	// �����û��ύ�Ĵ����WrongCases,Solution,Problems,StudentExamDetail�������Ϣ,����solutionId
	public String wsSubmitCode(String userName, String password, String codeXml);

	// �ύ�������
	public String wsSubmitThisProblem(String userName, String password,
			String codeXml);

	// ��ȡsubmittedcode����Ϣ����������ƶ�
	public String wsSubmittedCode(String userName, String password,
			int problemId);

	// �鿴��������,��ȡwrongcases����Ϣ
	public String wsViewWrongCase(String userName, String password,
			int examId, int problemId, int caseId, boolean caseInfo);

	// ��ȡ����״̬
	public String wsGetExamProblemStatus(String userName, String password,
			int examId, int problemId);

	// ���л���ȡsolution����Ϣ
	public String wsGetSolutions(String account,String password,int number);

	// ���²��к󷵻صĽ��
	public String wsUpdateResult(String userName, String password,byte[] result);

	// �Ƿ�������
	public String wsIsPermit(String userName, String password, int examId,
			String UUID);
	
	// ���л���ȡ��Ŀ��Ϣ
	public byte[] wsGetProblem4Judge(String userName, String password, int problemId);
	
	
	// ����examId��ȡ����ѵ����Ŀ���
	public String wsGetExamProCatagorys(String userName,String password,int examId);
	
	//����ѵ��ҳ����"��ʼ����"����
	public String wsCanDoCategory(String userName, String password, int examId);
	
	//����ѵ��ҳ����"��Ҫ����"����
	public byte[] wsDrawProblem(String userName, String password, int examId,int catId);
	
	//����ѵ��ģ�������¼ҳ������
	public String wsSubmitHistory(String userName, String password, int examId);
	
	//����ѵ��ģ��"�ύ����"����
	public String wsItrainSubmitCode(String userName, String password,int catId,String codeXml);
	
	//����ѵ��ģ��"��ʱ��������"����
	public String wsSkipThisProblem(String userName, String password, int examId,int catId,int problemId);
	
	//����ѵ��ģ��"�ύ����"����
	public String wsItrainSubmitThisProblem(String userName, String password,int catId,String codeXml,String continueTrain); 

	//����ѵ��ģ��"��Ҫͨ��"����
	public String wsPassThisCategory(String userName, String password, int examId,int catId);
}
