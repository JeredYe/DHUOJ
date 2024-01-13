
package edu.dhu.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.dhu.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _WSGetExamProCatagorysResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_GetExamProCatagorysResponse");
    private final static QName _WSGetExamByIdResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_GetExamByIdResponse");
    private final static QName _WSGetExamList_QNAME = new QName("http://ws.dhu.edu/", "WS_GetExamList");
    private final static QName _WSGetExamProblemsResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_GetExamProblemsResponse");
    private final static QName _WSGetProblem_QNAME = new QName("http://ws.dhu.edu/", "WS_GetProblem");
    private final static QName _WSDrawProblem_QNAME = new QName("http://ws.dhu.edu/", "WS_DrawProblem");
    private final static QName _WSGetProblem4JudgeResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_GetProblem4JudgeResponse");
    private final static QName _WSViewWrongCaseResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_ViewWrongCaseResponse");
    private final static QName _WSItrainSubmitCodeResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_ItrainSubmitCodeResponse");
    private final static QName _WSSubmittedCode_QNAME = new QName("http://ws.dhu.edu/", "WS_SubmittedCode");
    private final static QName _WSSkipThisProblem_QNAME = new QName("http://ws.dhu.edu/", "WS_SkipThisProblem");
    private final static QName _WSUpdateResultResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_UpdateResultResponse");
    private final static QName _WSItrainSubmitCode_QNAME = new QName("http://ws.dhu.edu/", "WS_ItrainSubmitCode");
    private final static QName _WSGetExamById_QNAME = new QName("http://ws.dhu.edu/", "WS_GetExamById");
    private final static QName _WSSubmitCode_QNAME = new QName("http://ws.dhu.edu/", "WS_SubmitCode");
    private final static QName _WSPassThisCategory_QNAME = new QName("http://ws.dhu.edu/", "WS_PassThisCategory");
    private final static QName _WSItrainSubmitThisProblemResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_ItrainSubmitThisProblemResponse");
    private final static QName _WSGetProblemResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_GetProblemResponse");
    private final static QName _WSIsPermitResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_IsPermitResponse");
    private final static QName _WSCanDoCategory_QNAME = new QName("http://ws.dhu.edu/", "WS_CanDoCategory");
    private final static QName _WSUpdateResult_QNAME = new QName("http://ws.dhu.edu/", "WS_UpdateResult");
    private final static QName _WSGetExamProCatagorys_QNAME = new QName("http://ws.dhu.edu/", "WS_GetExamProCatagorys");
    private final static QName _WSItrainSubmitThisProblem_QNAME = new QName("http://ws.dhu.edu/", "WS_ItrainSubmitThisProblem");
    private final static QName _WSCanDoCategoryResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_CanDoCategoryResponse");
    private final static QName _WSSubmitThisProblem_QNAME = new QName("http://ws.dhu.edu/", "WS_SubmitThisProblem");
    private final static QName _WSSubmitThisProblemResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_SubmitThisProblemResponse");
    private final static QName _WSLogin_QNAME = new QName("http://ws.dhu.edu/", "WS_Login");
    private final static QName _WSSubmitHistoryResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_SubmitHistoryResponse");
    private final static QName _WSSubmittedCodeResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_SubmittedCodeResponse");
    private final static QName _WSGetExamDetailResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_GetExamDetailResponse");
    private final static QName _WSGetSolutions_QNAME = new QName("http://ws.dhu.edu/", "WS_GetSolutions");
    private final static QName _WSSubmitCodeResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_SubmitCodeResponse");
    private final static QName _WSPassThisCategoryResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_PassThisCategoryResponse");
    private final static QName _WSGetExamListResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_GetExamListResponse");
    private final static QName _WSLoginResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_LoginResponse");
    private final static QName _WSViewWrongCase_QNAME = new QName("http://ws.dhu.edu/", "WS_ViewWrongCase");
    private final static QName _WSSkipThisProblemResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_SkipThisProblemResponse");
    private final static QName _WSSubmitHistory_QNAME = new QName("http://ws.dhu.edu/", "WS_SubmitHistory");
    private final static QName _WSDrawProblemResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_DrawProblemResponse");
    private final static QName _WSGetProblem4Judge_QNAME = new QName("http://ws.dhu.edu/", "WS_GetProblem4Judge");
    private final static QName _WSGetExamProblemStatus_QNAME = new QName("http://ws.dhu.edu/", "WS_GetExamProblemStatus");
    private final static QName _WSIsPermit_QNAME = new QName("http://ws.dhu.edu/", "WS_IsPermit");
    private final static QName _WSGetExamProblems_QNAME = new QName("http://ws.dhu.edu/", "WS_GetExamProblems");
    private final static QName _WSGetExamProblemStatusResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_GetExamProblemStatusResponse");
    private final static QName _WSGetExamDetail_QNAME = new QName("http://ws.dhu.edu/", "WS_GetExamDetail");
    private final static QName _WSGetSolutionsResponse_QNAME = new QName("http://ws.dhu.edu/", "WS_GetSolutionsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.dhu.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WSSubmitThisProblem }
     * 
     */
    public WSSubmitThisProblem createWSSubmitThisProblem() {
        return new WSSubmitThisProblem();
    }

    /**
     * Create an instance of {@link WSCanDoCategoryResponse }
     * 
     */
    public WSCanDoCategoryResponse createWSCanDoCategoryResponse() {
        return new WSCanDoCategoryResponse();
    }

    /**
     * Create an instance of {@link WSSubmitThisProblemResponse }
     * 
     */
    public WSSubmitThisProblemResponse createWSSubmitThisProblemResponse() {
        return new WSSubmitThisProblemResponse();
    }

    /**
     * Create an instance of {@link WSPassThisCategoryResponse }
     * 
     */
    public WSPassThisCategoryResponse createWSPassThisCategoryResponse() {
        return new WSPassThisCategoryResponse();
    }

    /**
     * Create an instance of {@link WSGetSolutions }
     * 
     */
    public WSGetSolutions createWSGetSolutions() {
        return new WSGetSolutions();
    }

    /**
     * Create an instance of {@link WSSubmitCodeResponse }
     * 
     */
    public WSSubmitCodeResponse createWSSubmitCodeResponse() {
        return new WSSubmitCodeResponse();
    }

    /**
     * Create an instance of {@link WSSkipThisProblemResponse }
     * 
     */
    public WSSkipThisProblemResponse createWSSkipThisProblemResponse() {
        return new WSSkipThisProblemResponse();
    }

    /**
     * Create an instance of {@link WSSubmitHistory }
     * 
     */
    public WSSubmitHistory createWSSubmitHistory() {
        return new WSSubmitHistory();
    }

    /**
     * Create an instance of {@link WSGetExamListResponse }
     * 
     */
    public WSGetExamListResponse createWSGetExamListResponse() {
        return new WSGetExamListResponse();
    }

    /**
     * Create an instance of {@link WSLoginResponse }
     * 
     */
    public WSLoginResponse createWSLoginResponse() {
        return new WSLoginResponse();
    }

    /**
     * Create an instance of {@link WSViewWrongCase }
     * 
     */
    public WSViewWrongCase createWSViewWrongCase() {
        return new WSViewWrongCase();
    }

    /**
     * Create an instance of {@link WSLogin }
     * 
     */
    public WSLogin createWSLogin() {
        return new WSLogin();
    }

    /**
     * Create an instance of {@link WSSubmitHistoryResponse }
     * 
     */
    public WSSubmitHistoryResponse createWSSubmitHistoryResponse() {
        return new WSSubmitHistoryResponse();
    }

    /**
     * Create an instance of {@link WSSubmittedCodeResponse }
     * 
     */
    public WSSubmittedCodeResponse createWSSubmittedCodeResponse() {
        return new WSSubmittedCodeResponse();
    }

    /**
     * Create an instance of {@link WSGetExamDetailResponse }
     * 
     */
    public WSGetExamDetailResponse createWSGetExamDetailResponse() {
        return new WSGetExamDetailResponse();
    }

    /**
     * Create an instance of {@link WSIsPermit }
     * 
     */
    public WSIsPermit createWSIsPermit() {
        return new WSIsPermit();
    }

    /**
     * Create an instance of {@link WSGetExamProblemStatus }
     * 
     */
    public WSGetExamProblemStatus createWSGetExamProblemStatus() {
        return new WSGetExamProblemStatus();
    }

    /**
     * Create an instance of {@link WSGetExamProblems }
     * 
     */
    public WSGetExamProblems createWSGetExamProblems() {
        return new WSGetExamProblems();
    }

    /**
     * Create an instance of {@link WSDrawProblemResponse }
     * 
     */
    public WSDrawProblemResponse createWSDrawProblemResponse() {
        return new WSDrawProblemResponse();
    }

    /**
     * Create an instance of {@link WSGetProblem4Judge }
     * 
     */
    public WSGetProblem4Judge createWSGetProblem4Judge() {
        return new WSGetProblem4Judge();
    }

    /**
     * Create an instance of {@link WSGetExamProblemStatusResponse }
     * 
     */
    public WSGetExamProblemStatusResponse createWSGetExamProblemStatusResponse() {
        return new WSGetExamProblemStatusResponse();
    }

    /**
     * Create an instance of {@link WSGetExamDetail }
     * 
     */
    public WSGetExamDetail createWSGetExamDetail() {
        return new WSGetExamDetail();
    }

    /**
     * Create an instance of {@link WSGetSolutionsResponse }
     * 
     */
    public WSGetSolutionsResponse createWSGetSolutionsResponse() {
        return new WSGetSolutionsResponse();
    }

    /**
     * Create an instance of {@link WSDrawProblem }
     * 
     */
    public WSDrawProblem createWSDrawProblem() {
        return new WSDrawProblem();
    }

    /**
     * Create an instance of {@link WSGetProblem4JudgeResponse }
     * 
     */
    public WSGetProblem4JudgeResponse createWSGetProblem4JudgeResponse() {
        return new WSGetProblem4JudgeResponse();
    }

    /**
     * Create an instance of {@link WSViewWrongCaseResponse }
     * 
     */
    public WSViewWrongCaseResponse createWSViewWrongCaseResponse() {
        return new WSViewWrongCaseResponse();
    }

    /**
     * Create an instance of {@link WSGetExamByIdResponse }
     * 
     */
    public WSGetExamByIdResponse createWSGetExamByIdResponse() {
        return new WSGetExamByIdResponse();
    }

    /**
     * Create an instance of {@link WSGetExamProCatagorysResponse }
     * 
     */
    public WSGetExamProCatagorysResponse createWSGetExamProCatagorysResponse() {
        return new WSGetExamProCatagorysResponse();
    }

    /**
     * Create an instance of {@link WSGetProblem }
     * 
     */
    public WSGetProblem createWSGetProblem() {
        return new WSGetProblem();
    }

    /**
     * Create an instance of {@link WSGetExamList }
     * 
     */
    public WSGetExamList createWSGetExamList() {
        return new WSGetExamList();
    }

    /**
     * Create an instance of {@link WSGetExamProblemsResponse }
     * 
     */
    public WSGetExamProblemsResponse createWSGetExamProblemsResponse() {
        return new WSGetExamProblemsResponse();
    }

    /**
     * Create an instance of {@link WSUpdateResultResponse }
     * 
     */
    public WSUpdateResultResponse createWSUpdateResultResponse() {
        return new WSUpdateResultResponse();
    }

    /**
     * Create an instance of {@link WSItrainSubmitCode }
     * 
     */
    public WSItrainSubmitCode createWSItrainSubmitCode() {
        return new WSItrainSubmitCode();
    }

    /**
     * Create an instance of {@link WSSkipThisProblem }
     * 
     */
    public WSSkipThisProblem createWSSkipThisProblem() {
        return new WSSkipThisProblem();
    }

    /**
     * Create an instance of {@link WSItrainSubmitCodeResponse }
     * 
     */
    public WSItrainSubmitCodeResponse createWSItrainSubmitCodeResponse() {
        return new WSItrainSubmitCodeResponse();
    }

    /**
     * Create an instance of {@link WSSubmittedCode }
     * 
     */
    public WSSubmittedCode createWSSubmittedCode() {
        return new WSSubmittedCode();
    }

    /**
     * Create an instance of {@link WSPassThisCategory }
     * 
     */
    public WSPassThisCategory createWSPassThisCategory() {
        return new WSPassThisCategory();
    }

    /**
     * Create an instance of {@link WSItrainSubmitThisProblemResponse }
     * 
     */
    public WSItrainSubmitThisProblemResponse createWSItrainSubmitThisProblemResponse() {
        return new WSItrainSubmitThisProblemResponse();
    }

    /**
     * Create an instance of {@link WSSubmitCode }
     * 
     */
    public WSSubmitCode createWSSubmitCode() {
        return new WSSubmitCode();
    }

    /**
     * Create an instance of {@link WSGetExamById }
     * 
     */
    public WSGetExamById createWSGetExamById() {
        return new WSGetExamById();
    }

    /**
     * Create an instance of {@link WSCanDoCategory }
     * 
     */
    public WSCanDoCategory createWSCanDoCategory() {
        return new WSCanDoCategory();
    }

    /**
     * Create an instance of {@link WSUpdateResult }
     * 
     */
    public WSUpdateResult createWSUpdateResult() {
        return new WSUpdateResult();
    }

    /**
     * Create an instance of {@link WSGetExamProCatagorys }
     * 
     */
    public WSGetExamProCatagorys createWSGetExamProCatagorys() {
        return new WSGetExamProCatagorys();
    }

    /**
     * Create an instance of {@link WSItrainSubmitThisProblem }
     * 
     */
    public WSItrainSubmitThisProblem createWSItrainSubmitThisProblem() {
        return new WSItrainSubmitThisProblem();
    }

    /**
     * Create an instance of {@link WSIsPermitResponse }
     * 
     */
    public WSIsPermitResponse createWSIsPermitResponse() {
        return new WSIsPermitResponse();
    }

    /**
     * Create an instance of {@link WSGetProblemResponse }
     * 
     */
    public WSGetProblemResponse createWSGetProblemResponse() {
        return new WSGetProblemResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSGetExamProCatagorysResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_GetExamProCatagorysResponse")
    public JAXBElement<WSGetExamProCatagorysResponse> createWSGetExamProCatagorysResponse(WSGetExamProCatagorysResponse value) {
        return new JAXBElement<WSGetExamProCatagorysResponse>(_WSGetExamProCatagorysResponse_QNAME, WSGetExamProCatagorysResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSGetExamByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_GetExamByIdResponse")
    public JAXBElement<WSGetExamByIdResponse> createWSGetExamByIdResponse(WSGetExamByIdResponse value) {
        return new JAXBElement<WSGetExamByIdResponse>(_WSGetExamByIdResponse_QNAME, WSGetExamByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSGetExamList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_GetExamList")
    public JAXBElement<WSGetExamList> createWSGetExamList(WSGetExamList value) {
        return new JAXBElement<WSGetExamList>(_WSGetExamList_QNAME, WSGetExamList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSGetExamProblemsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_GetExamProblemsResponse")
    public JAXBElement<WSGetExamProblemsResponse> createWSGetExamProblemsResponse(WSGetExamProblemsResponse value) {
        return new JAXBElement<WSGetExamProblemsResponse>(_WSGetExamProblemsResponse_QNAME, WSGetExamProblemsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSGetProblem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_GetProblem")
    public JAXBElement<WSGetProblem> createWSGetProblem(WSGetProblem value) {
        return new JAXBElement<WSGetProblem>(_WSGetProblem_QNAME, WSGetProblem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSDrawProblem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_DrawProblem")
    public JAXBElement<WSDrawProblem> createWSDrawProblem(WSDrawProblem value) {
        return new JAXBElement<WSDrawProblem>(_WSDrawProblem_QNAME, WSDrawProblem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSGetProblem4JudgeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_GetProblem4JudgeResponse")
    public JAXBElement<WSGetProblem4JudgeResponse> createWSGetProblem4JudgeResponse(WSGetProblem4JudgeResponse value) {
        return new JAXBElement<WSGetProblem4JudgeResponse>(_WSGetProblem4JudgeResponse_QNAME, WSGetProblem4JudgeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSViewWrongCaseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_ViewWrongCaseResponse")
    public JAXBElement<WSViewWrongCaseResponse> createWSViewWrongCaseResponse(WSViewWrongCaseResponse value) {
        return new JAXBElement<WSViewWrongCaseResponse>(_WSViewWrongCaseResponse_QNAME, WSViewWrongCaseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSItrainSubmitCodeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_ItrainSubmitCodeResponse")
    public JAXBElement<WSItrainSubmitCodeResponse> createWSItrainSubmitCodeResponse(WSItrainSubmitCodeResponse value) {
        return new JAXBElement<WSItrainSubmitCodeResponse>(_WSItrainSubmitCodeResponse_QNAME, WSItrainSubmitCodeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSSubmittedCode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_SubmittedCode")
    public JAXBElement<WSSubmittedCode> createWSSubmittedCode(WSSubmittedCode value) {
        return new JAXBElement<WSSubmittedCode>(_WSSubmittedCode_QNAME, WSSubmittedCode.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSSkipThisProblem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_SkipThisProblem")
    public JAXBElement<WSSkipThisProblem> createWSSkipThisProblem(WSSkipThisProblem value) {
        return new JAXBElement<WSSkipThisProblem>(_WSSkipThisProblem_QNAME, WSSkipThisProblem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSUpdateResultResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_UpdateResultResponse")
    public JAXBElement<WSUpdateResultResponse> createWSUpdateResultResponse(WSUpdateResultResponse value) {
        return new JAXBElement<WSUpdateResultResponse>(_WSUpdateResultResponse_QNAME, WSUpdateResultResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSItrainSubmitCode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_ItrainSubmitCode")
    public JAXBElement<WSItrainSubmitCode> createWSItrainSubmitCode(WSItrainSubmitCode value) {
        return new JAXBElement<WSItrainSubmitCode>(_WSItrainSubmitCode_QNAME, WSItrainSubmitCode.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSGetExamById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_GetExamById")
    public JAXBElement<WSGetExamById> createWSGetExamById(WSGetExamById value) {
        return new JAXBElement<WSGetExamById>(_WSGetExamById_QNAME, WSGetExamById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSSubmitCode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_SubmitCode")
    public JAXBElement<WSSubmitCode> createWSSubmitCode(WSSubmitCode value) {
        return new JAXBElement<WSSubmitCode>(_WSSubmitCode_QNAME, WSSubmitCode.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSPassThisCategory }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_PassThisCategory")
    public JAXBElement<WSPassThisCategory> createWSPassThisCategory(WSPassThisCategory value) {
        return new JAXBElement<WSPassThisCategory>(_WSPassThisCategory_QNAME, WSPassThisCategory.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSItrainSubmitThisProblemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_ItrainSubmitThisProblemResponse")
    public JAXBElement<WSItrainSubmitThisProblemResponse> createWSItrainSubmitThisProblemResponse(WSItrainSubmitThisProblemResponse value) {
        return new JAXBElement<WSItrainSubmitThisProblemResponse>(_WSItrainSubmitThisProblemResponse_QNAME, WSItrainSubmitThisProblemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSGetProblemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_GetProblemResponse")
    public JAXBElement<WSGetProblemResponse> createWSGetProblemResponse(WSGetProblemResponse value) {
        return new JAXBElement<WSGetProblemResponse>(_WSGetProblemResponse_QNAME, WSGetProblemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSIsPermitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_IsPermitResponse")
    public JAXBElement<WSIsPermitResponse> createWSIsPermitResponse(WSIsPermitResponse value) {
        return new JAXBElement<WSIsPermitResponse>(_WSIsPermitResponse_QNAME, WSIsPermitResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSCanDoCategory }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_CanDoCategory")
    public JAXBElement<WSCanDoCategory> createWSCanDoCategory(WSCanDoCategory value) {
        return new JAXBElement<WSCanDoCategory>(_WSCanDoCategory_QNAME, WSCanDoCategory.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSUpdateResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_UpdateResult")
    public JAXBElement<WSUpdateResult> createWSUpdateResult(WSUpdateResult value) {
        return new JAXBElement<WSUpdateResult>(_WSUpdateResult_QNAME, WSUpdateResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSGetExamProCatagorys }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_GetExamProCatagorys")
    public JAXBElement<WSGetExamProCatagorys> createWSGetExamProCatagorys(WSGetExamProCatagorys value) {
        return new JAXBElement<WSGetExamProCatagorys>(_WSGetExamProCatagorys_QNAME, WSGetExamProCatagorys.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSItrainSubmitThisProblem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_ItrainSubmitThisProblem")
    public JAXBElement<WSItrainSubmitThisProblem> createWSItrainSubmitThisProblem(WSItrainSubmitThisProblem value) {
        return new JAXBElement<WSItrainSubmitThisProblem>(_WSItrainSubmitThisProblem_QNAME, WSItrainSubmitThisProblem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSCanDoCategoryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_CanDoCategoryResponse")
    public JAXBElement<WSCanDoCategoryResponse> createWSCanDoCategoryResponse(WSCanDoCategoryResponse value) {
        return new JAXBElement<WSCanDoCategoryResponse>(_WSCanDoCategoryResponse_QNAME, WSCanDoCategoryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSSubmitThisProblem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_SubmitThisProblem")
    public JAXBElement<WSSubmitThisProblem> createWSSubmitThisProblem(WSSubmitThisProblem value) {
        return new JAXBElement<WSSubmitThisProblem>(_WSSubmitThisProblem_QNAME, WSSubmitThisProblem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSSubmitThisProblemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_SubmitThisProblemResponse")
    public JAXBElement<WSSubmitThisProblemResponse> createWSSubmitThisProblemResponse(WSSubmitThisProblemResponse value) {
        return new JAXBElement<WSSubmitThisProblemResponse>(_WSSubmitThisProblemResponse_QNAME, WSSubmitThisProblemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSLogin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_Login")
    public JAXBElement<WSLogin> createWSLogin(WSLogin value) {
        return new JAXBElement<WSLogin>(_WSLogin_QNAME, WSLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSSubmitHistoryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_SubmitHistoryResponse")
    public JAXBElement<WSSubmitHistoryResponse> createWSSubmitHistoryResponse(WSSubmitHistoryResponse value) {
        return new JAXBElement<WSSubmitHistoryResponse>(_WSSubmitHistoryResponse_QNAME, WSSubmitHistoryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSSubmittedCodeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_SubmittedCodeResponse")
    public JAXBElement<WSSubmittedCodeResponse> createWSSubmittedCodeResponse(WSSubmittedCodeResponse value) {
        return new JAXBElement<WSSubmittedCodeResponse>(_WSSubmittedCodeResponse_QNAME, WSSubmittedCodeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSGetExamDetailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_GetExamDetailResponse")
    public JAXBElement<WSGetExamDetailResponse> createWSGetExamDetailResponse(WSGetExamDetailResponse value) {
        return new JAXBElement<WSGetExamDetailResponse>(_WSGetExamDetailResponse_QNAME, WSGetExamDetailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSGetSolutions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_GetSolutions")
    public JAXBElement<WSGetSolutions> createWSGetSolutions(WSGetSolutions value) {
        return new JAXBElement<WSGetSolutions>(_WSGetSolutions_QNAME, WSGetSolutions.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSSubmitCodeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_SubmitCodeResponse")
    public JAXBElement<WSSubmitCodeResponse> createWSSubmitCodeResponse(WSSubmitCodeResponse value) {
        return new JAXBElement<WSSubmitCodeResponse>(_WSSubmitCodeResponse_QNAME, WSSubmitCodeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSPassThisCategoryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_PassThisCategoryResponse")
    public JAXBElement<WSPassThisCategoryResponse> createWSPassThisCategoryResponse(WSPassThisCategoryResponse value) {
        return new JAXBElement<WSPassThisCategoryResponse>(_WSPassThisCategoryResponse_QNAME, WSPassThisCategoryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSGetExamListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_GetExamListResponse")
    public JAXBElement<WSGetExamListResponse> createWSGetExamListResponse(WSGetExamListResponse value) {
        return new JAXBElement<WSGetExamListResponse>(_WSGetExamListResponse_QNAME, WSGetExamListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSLoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_LoginResponse")
    public JAXBElement<WSLoginResponse> createWSLoginResponse(WSLoginResponse value) {
        return new JAXBElement<WSLoginResponse>(_WSLoginResponse_QNAME, WSLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSViewWrongCase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_ViewWrongCase")
    public JAXBElement<WSViewWrongCase> createWSViewWrongCase(WSViewWrongCase value) {
        return new JAXBElement<WSViewWrongCase>(_WSViewWrongCase_QNAME, WSViewWrongCase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSSkipThisProblemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_SkipThisProblemResponse")
    public JAXBElement<WSSkipThisProblemResponse> createWSSkipThisProblemResponse(WSSkipThisProblemResponse value) {
        return new JAXBElement<WSSkipThisProblemResponse>(_WSSkipThisProblemResponse_QNAME, WSSkipThisProblemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSSubmitHistory }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_SubmitHistory")
    public JAXBElement<WSSubmitHistory> createWSSubmitHistory(WSSubmitHistory value) {
        return new JAXBElement<WSSubmitHistory>(_WSSubmitHistory_QNAME, WSSubmitHistory.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSDrawProblemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_DrawProblemResponse")
    public JAXBElement<WSDrawProblemResponse> createWSDrawProblemResponse(WSDrawProblemResponse value) {
        return new JAXBElement<WSDrawProblemResponse>(_WSDrawProblemResponse_QNAME, WSDrawProblemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSGetProblem4Judge }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_GetProblem4Judge")
    public JAXBElement<WSGetProblem4Judge> createWSGetProblem4Judge(WSGetProblem4Judge value) {
        return new JAXBElement<WSGetProblem4Judge>(_WSGetProblem4Judge_QNAME, WSGetProblem4Judge.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSGetExamProblemStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_GetExamProblemStatus")
    public JAXBElement<WSGetExamProblemStatus> createWSGetExamProblemStatus(WSGetExamProblemStatus value) {
        return new JAXBElement<WSGetExamProblemStatus>(_WSGetExamProblemStatus_QNAME, WSGetExamProblemStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSIsPermit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_IsPermit")
    public JAXBElement<WSIsPermit> createWSIsPermit(WSIsPermit value) {
        return new JAXBElement<WSIsPermit>(_WSIsPermit_QNAME, WSIsPermit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSGetExamProblems }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_GetExamProblems")
    public JAXBElement<WSGetExamProblems> createWSGetExamProblems(WSGetExamProblems value) {
        return new JAXBElement<WSGetExamProblems>(_WSGetExamProblems_QNAME, WSGetExamProblems.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSGetExamProblemStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_GetExamProblemStatusResponse")
    public JAXBElement<WSGetExamProblemStatusResponse> createWSGetExamProblemStatusResponse(WSGetExamProblemStatusResponse value) {
        return new JAXBElement<WSGetExamProblemStatusResponse>(_WSGetExamProblemStatusResponse_QNAME, WSGetExamProblemStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSGetExamDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_GetExamDetail")
    public JAXBElement<WSGetExamDetail> createWSGetExamDetail(WSGetExamDetail value) {
        return new JAXBElement<WSGetExamDetail>(_WSGetExamDetail_QNAME, WSGetExamDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSGetSolutionsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dhu.edu/", name = "WS_GetSolutionsResponse")
    public JAXBElement<WSGetSolutionsResponse> createWSGetSolutionsResponse(WSGetSolutionsResponse value) {
        return new JAXBElement<WSGetSolutionsResponse>(_WSGetSolutionsResponse_QNAME, WSGetSolutionsResponse.class, null, value);
    }

}
