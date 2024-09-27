package redsoft.voc.common.common.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import redsoft.frm.startup.SYSTEM;
import redsoft.frm.util.StringUtil;

public class SelectThread {

	private static int MAX_ROW = 1000;

	List<Map<String, Object>> synchronizedList = Collections.synchronizedList(new ArrayList<Map<String, Object>>());

	boolean checkExcel = false;

	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	String strQuery = "";

	void addList(HashMap<String, Object> row) {
		synchronized (row) {
			synchronizedList.add(row);
		}
	}

	public List<Map<String, Object>> getList() {
		return this.synchronizedList;
	}

	public void setListInit() {
		synchronizedList.clear();
	}

	public boolean checkExcel(int row) {
		if (synchronizedList.size() == row) {
			this.checkExcel = true;
		}
		return this.checkExcel;
	}

	public class ThreadAll implements Runnable {

		Map<String, Object> key = new HashMap<String, Object>();
		int size;

		public ThreadAll(int size, Map<String, Object> preKey) {
			this.size = size;
			this.key = preKey;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ExecutorService execService = Executors.newFixedThreadPool(size);
			for (int i = 0; i < size;) {
				execService.execute(new MyThread((((i + 1) * MAX_ROW) - MAX_ROW) + 1, (i + 1) * MAX_ROW, key));
				i++;
			}
		}

	}

	public class MyThread implements Runnable {
		String strQuery;

		public MyThread(int startNo, int endNo, Map<String, Object> key) {
			String str = null;
			switch (String.valueOf(key.get("Type"))) {
			case "Customervoice0100XlsView": {
				str = " SELECT " + "    a.acepno," + "    a.acepno AS acepno_format,"
						+ "    fn_get_user_nm(a.acepprsn_id,'NM') acepprsn_nm," + "    a.acepprsn_id,"
						+ "    a.acep_date," + "    a.acep_div_cd," + "    ( " + "        SELECT "
						+ "            x.cd_knm" + "        FROM " + "            clt_comm_cd x" + "        WHERE "
						+ "            x.cd_id = 'N010'" + "            AND   x.cd_tp = a.acep_div_cd"
						+ "    ) acep_div_cd_nm," + "    a.cncl_type_cd," + "    a.prod_nm,"
						+ "    DECODE(a.cncl_type_cd,'1',a.claim_l_type_cd_nm,'3',a.claim_l_type_cd_nm,a.lcls_type_cd_nm) AS lcls_type_cd_nm,"
						+ "    DECODE(a.cncl_type_cd,'1',a.type_cd_nm,'3',a.type_cd_nm,a.scls_type_cd_nm) AS scls_type_cd_nm,"
						+ "    a.cncl_detl," + "    a.approval_needs_yn,"
						+ "    DECODE(a.stat_cd,'T','기안함','R','결재중','P','결재중','Y','결재완료 : ' "
						+ "    || fn_get_user_nm(a.chg_id,'NM'),DECODE(a.approval_needs_yn,'Y','결재상신필요') ) approval_needs_yn_nm,"
						+ "    a.record_url," + "    a.total_cnt " + "FROM " + "    ( " + "        SELECT "
						+ "            ROWNUM AS rnum," + "            a.*" + "        FROM " + "            ("
						+ "                SELECT " + "                    a.acepno,"
						+ "                    a.acep_date," + "                    a.cncl_type_cd,"
						+ "                    a.acep_div_cd," + "                    a.acepprsn_id,"
						+ "                    a.prod_nm," + "                    ( "
						+ "                        SELECT " + "                            x.code_name"
						+ "                        FROM " + "                            ncc_s_common_code x"
						+ "                        WHERE " + "                            x.code = a.lcls_type_cd"
						+ "                    ) lcls_type_cd_nm," + "                    ( "
						+ "                        SELECT " + "                            x.code_name"
						+ "                        FROM " + "                            ncc_s_common_code x"
						+ "                        WHERE " + "                            x.code = a.scls_type_cd"
						+ "                    ) scls_type_cd_nm," + "                    ( "
						+ "                        SELECT " + "                            x.type_name"
						+ "                        FROM" + "                            clt_claim_type_cd x"
						+ "                        WHERE" + "                            x.type_cd = a.claim_l_type_cd"
						+ "                            AND   type_dvn = '1'"
						+ "                    ) claim_l_type_cd_nm," + "                    ( "
						+ "                        SELECT " + "                            x.type_name"
						+ "                        FROM " + "                            clt_claim_type_cd x"
						+ "                        WHERE " + "                            x.type_cd = a.type_cd"
						+ "                            AND   type_dvn = '1'" + "                    ) type_cd_nm,"
						+ "                    DECODE(a.cncl_type_cd,'1',a.cncl_detl,'2',DECODE(a.acep_div_cd,'2',a.qstn_tit,a.cncl_detl) ) AS cncl_detl,"
						+ "                    a.approval_needs_yn," + "                    b.stat_cd,"
						+ "                    b.chg_id," + "                    c.record_url,"
						+ "                    COUNT(a.acepno) OVER() total_cnt" + "                FROM"
						+ "                    clt_claim_mst a," + "                    ( "
						+ "                        SELECT " + "                            appl_id,"
						+ "                            stat_cd," + "                            chg_id "
						+ "                        FROM " + "                            nhapr200"
						+ "                        WHERE " + "                            seq = '0'"
						+ "                    ) b," + "                    ( " + "                        SELECT "
						+ "                            x.accept_no," + "                            record_url"
						+ "                        FROM " + "                            ncc_t_consult x "
						+ "                    ) c" + "                WHERE"
						+ "                    a.acepno = b.appl_id (+)"
						+ "                    AND   a.acepno = c.accept_no (+)"
						+ "                    AND   nvl(answ_type,'03') = '03'"
						+ "                    AND   a.acep_date BETWEEN '" + key.get("schStartDd") + "'"
						+ "                    || '000000' AND '" + key.get("schEndDd") + "'"
						+ "                    || '999999'" + "                ORDER BY "
						+ "                    acep_date DESC " + "            ) a " + "        WHERE"
						+ "            1 = 1 " + "			   AND ROWNUM <= " + endNo + "    ) a " + "WHERE"
						+ "    1 = 1 " + "    AND   rnum >= " + startNo;
				break;
			}
			case "Mgmng0100XlsView": {
				str = " SELECT   A.ACEPNO" + "           , A.ACEPNO AS ACEPNO_FORMAT " + "           , A.CLIENT_NM "
						+ "           , A.CNCL_TYPE_CD " + "           , A.ANSW_TYPE "
						+ "           , CASE WHEN A.CNCL_TYPE_CD = '2'  "
						+ "             THEN (SELECT X.CD_KNM FROM CLT_COMM_CD X WHERE X.CD_ID = 'N001' AND X.CD_TP = A.PROGR_STAT)  "
						+ "             ELSE A.PROGR_STAT END AS PROGR_STAT "
						+ "           , DECODE(A.CNCL_TYPE_CD, '1', A.CLAIM_L_TYPE_CD, '3', A.CLAIM_L_TYPE_CD, A.LCLS_TYPE_CD) AS LCLS_TYPE_CD "
						+ "           , DECODE(A.CNCL_TYPE_CD, '1', A.CLAIM_M_TYPE_CD, '3', A.CLAIM_M_TYPE_CD, A.MCLS_TYPE_CD) AS MCLS_TYPE_CD "
						+ "           , DECODE(A.CNCL_TYPE_CD, '1', A.TYPE_CD, '3', A.TYPE_CD, A.SCLS_TYPE_CD) AS SCLS_TYPE_CD "
						+ "           , DECODE(A.CNCL_TYPE_CD, '1', A.CLAIM_L_TYPE_CD_NM, '3', A.CLAIM_L_TYPE_CD_NM, A.LCLS_TYPE_CD_NM) AS LCLS_TYPE_CD_NM "
						+ "           , DECODE(A.CNCL_TYPE_CD, '1', A.CLAIM_M_TYPE_CD_NM, '3', A.CLAIM_M_TYPE_CD_NM, A.MCLS_TYPE_CD_NM) AS MCLS_TYPE_CD_NM "
						+ "           , DECODE(A.CNCL_TYPE_CD, '1', A.TYPE_CD_NM, '3', A.TYPE_CD_NM, A.SCLS_TYPE_CD_NM) AS SCLS_TYPE_CD_NM "
						+ "			  , A.MANF_FACT_CD "	
						+ "			  , (SELECT CD_KNM FROM CLT_COMM_CD WHERE CD_ID = 'N013' AND CD_TP = A.MANF_FACT_CD) MANF_FACT_NM "
						+ "           , DECODE(A.FIRST_ACEP_CHNL, '2', DECODE(A.QSTN_TIT, '', A.CNCL_DETL, '*홈페이지 접수제목: '||A.QSTN_TIT || '   *접수내용: '||A.QSTN_CNTN), DECODE(A.CNCL_DETL, '', A.QSTN_TIT, A.CNCL_DETL)) AS CNCL_DETL "
						+ "           , A.QSTN_CNTN "
						+ "           , decode(A.CNCL_TYPE_CD, '2',(select max(product_name) from ncc_t_consult where accept_no = a.acepno),A.PROD_NM) PROD_NM "
						+ "           , A.ACEP_DATE " + "           , A.ACEP_DIV_CD "
						+ "           , FN_GET_USER_NM(A.ACEPPRSN_ID, 'NM') AS ACEPPRSN_NM "
						+ "           , A.FIRST_ACEP_CHNL "
						+ "           , (SELECT X.CD_KNM FROM CLT_COMM_CD X WHERE X.CD_ID = 'N010' AND X.CD_TP = A.FIRST_ACEP_CHNL ) FIRST_ACEP_CHNL_NM "
						+ "           , (SELECT X.CD_KNM FROM CLT_COMM_CD X WHERE X.CD_ID = 'N010' AND X.CD_TP = A.ACEP_DIV_CD ) ACEP_DIV_CD_NM "
						+ "           , A.CONSULT_CONTENT " + "           , NVL(A.REQUEST_CONTENT, ' ') AS REQUEST_CONTENT "
						+ "           , A.CUST_CONTINENT " + "           , A.CUST_COUNTRY " + "      FROM ( "
						+ "        SELECT   ROWNUM as RNUM, A.* " + "          FROM ( " + "           "
						+ "          	select aa.*  " + "	        from " + "	        ( "
						+ "            SELECT   A.CNCL_TYPE_CD, A.ACEPNO, NVL(CUSTOMER_NAME, A.CLIENT_NM) AS CLIENT_NM, A.ACEP_DATE, A.ACEP_DIV_CD "
						+ "                   , ACEPPRSN_ID , A.PROD_NM, A.LCLS_TYPE_CD, A.MCLS_TYPE_CD, A.SCLS_TYPE_CD "
						+ "                   , A.TYPE_CD, A.CLAIM_L_TYPE_CD, A.CLAIM_M_TYPE_CD "
						+ "                   , A.CNCL_DETL " + "                   , A.QSTN_TIT "
						+ "                   , A.QSTN_CNTN " + "                   , A.PROGR_STAT "
						+ "                   , A.ANSW_TYPE " + "                   , A.FIRST_ACEP_CHNL "
						+ "                   , A.MAXRETURN_YN  "
						+ "                   , (SELECT X.CODE_NAME FROM NCC_S_COMMON_CODE X WHERE X.CODE = A.LCLS_TYPE_CD ) LCLS_TYPE_CD_NM "
						+ "                   , (SELECT X.CODE_NAME FROM NCC_S_COMMON_CODE X WHERE X.CODE = A.MCLS_TYPE_CD ) MCLS_TYPE_CD_NM "
						+ "                   , (SELECT X.CODE_NAME FROM NCC_S_COMMON_CODE X WHERE X.CODE = A.SCLS_TYPE_CD ) SCLS_TYPE_CD_NM   "
						+ "                   , (SELECT X.TYPE_NAME FROM CLT_CLAIM_TYPE_CD X WHERE X.TYPE_CD = A.CLAIM_L_TYPE_CD AND  TYPE_DVN='1') CLAIM_L_TYPE_CD_NM "
						+ "                   , (SELECT X.TYPE_NAME FROM CLT_CLAIM_TYPE_CD X WHERE X.TYPE_CD = A.CLAIM_M_TYPE_CD AND  TYPE_DVN='1') CLAIM_M_TYPE_CD_NM "
						+ "                   , (SELECT X.TYPE_NAME FROM CLT_CLAIM_TYPE_CD X WHERE X.TYPE_CD = A.TYPE_CD AND  TYPE_DVN='1') TYPE_CD_NM                  "
						+ "                   , A.CNCL_DETL AS CONSULT_CONTENT "
						+ "                   , A.EXPLSUBJECT AS REQUEST_CONTENT "
						+ "                   , (select CD_KNM " + "                    from clt_comm_cd WHERE  "
						+ "                    CD_ID = 'N098' "
						+ "                    AND A.CUST_CONTINENT = CD_TP) CUST_CONTINENT "
						+ "                    , (select CD_KNM  " + "                    from clt_comm_cd WHERE  "
						+ "                    CD_ID = 'N099' "
						+ "                    AND A.CUST_COUNTRY = CD_TP) CUST_COUNTRY    "
						+ "                    ,(select max(emergency_yn) from ncc_t_consult where accept_no = a.acepno) emergency_yn "
						+ "                    ,a.manf_fact_cd "
						+ "              FROM CLT_CLAIM_MST A "
						+ "                 , NCC_T_CUSTOMER B                  "
						+ "             WHERE A.CUSTOMER_ID = B.CUSTOMER_ID(+)  "
						+ "               AND A.CNCL_TYPE_CD IN ('1','2')        "
						+ "               AND A.UP_ACEPNO IS NULL   ";

				if (!"Y".equals(StringUtil.trim(StringUtil.null2void(key.get("schInclYn"))))) {
					str += "      AND A.ACEPNO NOT LIKE '9%' ";
				}
				if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schCnclDetl")))) ) {
					str += "      AND DECODE(A.FIRST_ACEP_CHNL, '2', DECODE(A.QSTN_TIT,'',A.CNCL_DETL, A.QSTN_TIT), DECODE(A.CNCL_DETL,'',A.QSTN_TIT,A.CNCL_DETL)) LIKE '%' || '" + key.get("schCnclDetl") + "' || '%' ";
				}
				if (StringUtil.trim(StringUtil.null2void(key.get("schAcepStartDd"))) != "") {
					if (StringUtil.trim(StringUtil.null2void(key.get("schAcepEndDd"))) != "") {
						str += " AND A.ACEP_DATE BETWEEN '" + key.get("schAcepStartDd") + "' ||'000000' and '"
								+ key.get("schAcepEndDd") + "'||'999999'";
					}
				}
				if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schAcepno"))))) {
					str += "   AND A.APCENO = '" + key.get("schAcepno") + "' ";
				}
				if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schEmail")))) ) {
					str += "  AND A.CLIENT_EMAIL = '" + key.get("schEmail") + "' ";
				}
				if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schAcepDivCd"))))) {
					str += "  AND A.ACEP_DIV_CD = '" + key.get("schAcepDivCd") + "'";
				}
				if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("lclsTypeCd"))))) {
					str += "  AND A.LCLS_TYPE_CD = '" + key.get("lclsTypeCd") + "' AND A.CNCL_TYPE_CD = '2'";
				}
				if ("".equals(StringUtil.trim(StringUtil.null2void(key.get("lclsTypeCd"))))){
					if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schCnclTypeCd"))))) {
						str += "  AND A.CNCL_TYPE_CD = '" + key.get("schCnclTypeCd") + "'";
					}
				}
				if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("mclsTypeCd"))))) {
					str += "  AND A.MCLS_TYPE_CD = '" + key.get("mclsTypeCd") + "'";
				}
				if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("sclsTypeCd"))))) {
					str += "  AND A.SCLS_TYPE_CD = '" + key.get("sclsTypeCd") + "'";
				}
				if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schAnswType"))))) {
					str += "  AND A.ANSW_TYPE = '" + key.get("schAnswType") + "'";
				}
				if ("1".equals(StringUtil.trim(String.valueOf(key.get("schPrdctnLineLvl"))))) {
					if (!"".equals(StringUtil.trim(String.valueOf(key.get("schManfFactCd")))) && key.get("schManfFactCd") !=  null ) {
						str += "  AND A.MANF_FACT_CD = '" + key.get("schManfFactCd") + "'";
					}
				}
				if ("2".equals(StringUtil.trim(String.valueOf(key.get("schPrdctnLineLvl"))))) {
					if (!"".equals(StringUtil.trim(String.valueOf(key.get("schManfFactCd"))))&& key.get("schManfFactCd") !=  null) {
						str += "  AND A.MANF_FACT_CD = '" + key.get("schManfFactCd") + "'";
					}
					if (!"".equals(StringUtil.trim(String.valueOf(key.get("schManfDeptCd"))))&& key.get("schManfDeptCd") !=  null) {
						str += "  AND A.MANF_DEPT_CD = '" + key.get("schManfDeptCd") + "'";
					}
				}
				if ("3".equals(StringUtil.trim(String.valueOf(key.get("schPrdctnLineLvl"))))) {
					if (!"".equals(StringUtil.trim(String.valueOf(key.get("schManfFactCd"))))&& key.get("schManfFactCd") != null) {
						str += "  AND A.MANF_FACT_CD = '" + key.get("schManfFactCd") + "'";
					}
					if (!"".equals(StringUtil.trim(String.valueOf(key.get("schManfDeptCd"))))&& key.get("schManfDeptCd") !=  null) {
						str += "  AND A.MANF_DEPT_CD = '" + key.get("schManfDeptCd") + "'";
					}
					if (!"".equals(StringUtil.trim(String.valueOf(key.get("schManfLineCd"))))&& key.get("schManfLineCd") !=  null) {
						str += "  AND A.MANF_LINE_CD = '" + key.get("schManfLineCd") + "'";
					}
				}
				if (StringUtil.trim(StringUtil.null2void(key.get("schExpldocStartDd"))) != "") {
					if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schExpldocEndDd"))))
							&& key.get("schExpldocEndDd") != "null" && key.get("schExpldocEndDd") != null) {
						str += " AND B.EXPLDOC_PRESENT_LIMIT BETWEEN '" + key.get("schExpldocStartDd") + "' and '"
								+ key.get("schExpldocEndDd") + "' " + "  AND B.EXPLDOC_EXPL_CD = 'Y'";
					} else {
						str += "   AND B.EXPLDOC_PRESENT_LIMIT >= '" + key.get("schExpldocStartDd") + "'"
								+ "   AND B.EXPLDOC_EXPL_CD = 'Y'";
					}
				}
				if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schExpldocStartDd"))))) {
					if (key.get("schExpldocEndDd") != "" && key.get("schExpldocEndDd") != "null" && key.get("schExpldocEndDd") != null) {
						str += "  AND B.EXPLDOC_PRESENT_LIMIT <= '" + key.get("schExpldocEndDd")
								+ "  AND B.EXPLDOC_EXPL_CD = 'Y'";
					}
				}
				if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schProgrStat"))))) {
					if (key.get("schProgrStat") == "4") {
						str += " AND  A.PROGR_STAT > = '" + key.get("schProgrStat")
								+ "' AND a.CNCL_TYPE_CD = '1' AND A.PROGR_STAT <> 'Y'";
					} else {
						str += " AND  A.PROGR_STAT = '" + key.get("schProgrStat") + "'";
					}
				}
				if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schTypeCd"))))) {
					str += "  AND DECODE(a.cncl_type_cd,'1',a.claim_l_type_cd,'3',a.claim_l_type_cd,a.lcls_type_cd) = DECODE(SUBSTR('" + key.get("schTypeCd") +"',1,1),0, 'N0' || SUBSTR('"+key.get("schTypeCd")+"',2,1) || '0000', 'N' || '"+key.get("schTypeCd") + "' || '0000')";
//					str += "  AND A.CLAIM_L_TYPE_CD = 'N0" + key.get("schTypeCd") + "000'";
				}
				if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schTelno"))))) {
					str += "  AND (" + "                       B.MOBILE_NO1 LIKE '" + key.get("schTelno") + "' || '%'"
							+ "                    or B.MOBILE_NO2 LIKE '" + key.get("schTelno") + "' || '%'"
							+ "                    or B.PHONE_NO   LIKE '" + key.get("schTelno") + "' || '%'"
							+ "                    or B.OFFICE_PHONE_NO LIKE '" + key.get("schTelno") + "' || '%'"
							+ "                )";
				}
				if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schClientNm"))))) {
					str += "  AND  (" + "                B.CUSTOMER_NAME = '" + key.get("schClientNm") + "'"
							+ "                or" + "                A.CLIENT_NM = '" + key.get("schClientNm") + "'"
							+ "     )";
				}
				if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schAcepprsnId"))))) {
					str += "  AND A.ACEPPRSN_ID = '" + key.get("schAcepprsnId") + "'";
				}
				if ("01".equals(StringUtil.trim(String.valueOf(key.get("schChrgUserGubn"))))) {
					if (!"".equals(StringUtil.trim(String.valueOf(key.get("schProsClerk"))))) {
						str += "  AND (" + "                       EXISTS ("
								+ "                               SELECT 'X'"
								+ "                                 FROM CLT_CLAIM_MST_EXP_PROS X, CLT_CLAIM_MST Y"
								+ "                                WHERE X.ACEPNO = Y.ACEPNO(+)"
								+ "                                     AND Y.ACEPNO = A.ACEPNO "
								+ "                                     AND X.SEQ = '1'"
								+ "                                     AND DECODE(X.CLERK, NULL, (SELECT OPETR_ID FROM CLT_VISIT_OPETR_INFO WHERE PROS_DIV_CD = Y.PROS_DIV_CD_2), X.CLERK) =  '"
								+ key.get("schProsClerk") + "'" + "                              )"
								+ "                       )";
					}
				}
				if ("02".equals(StringUtil.trim(String.valueOf(key.get("schChrgUserGubn"))))) {
					if (!"".equals(StringUtil.trim(String.valueOf(key.get("schProsClerk"))))) {
						str += "  AND ( EXISTS" + "                             (SELECT 'X'"
								+ "                                 FROM"
								+ "                                   ( SELECT ROW_NUMBER() OVER (PARTITION BY ACEPNO ORDER BY SEQ DESC ) AS SEQ_NO, ACEPNO, CLERK"
								+ "                                       FROM CLT_CLAIM_MST_EXP_PROS "
								+ "                                      WHERE CLERK IS NOT NULL"
								+ "                                   ) X"
								+ "                                 WHERE A.ACEPNO = X.ACEPNO"
								+ "                                 AND CLERK = '" + key.get("schProsClerk") + "'"
								+ "                                 AND SEQ_NO = 1" + "                             ) "
								+ "                     )";
					}
				}
				if (!"".equals(StringUtil.trim(String.valueOf(key.get("schCausDivCdList")))) && key.get("schCausDivCdList")!=null ) {
					String[] stringArray = String.valueOf(key.get("schCausDivCdList")).split(",");
					str += " AND A.CAUS_DIV_CD in ( ";
					for (int i = 0; i < stringArray.length; i++) {
						str += " '" + stringArray[i] + "'";
						if (i < stringArray.length - 1) {
							str += ",";
						} else {
							str += ")";
						}
					}
				}
				if (!"".equals(StringUtil.trim(String.valueOf(key.get("schProsDivCdList")))) && key.get("schProsDivCdList")!=null ) {
					String[] stringArray = String.valueOf(key.get("schProsDivCdList")).split(",");
					str += " AND A.PROS_DIV_CD_1 in ( ";
					for (int i = 0; i < stringArray.length; i++) {
						str += " '" + stringArray[i] + "'";
						if (i < stringArray.length - 1) {
							str += ",";
						} else {
							str += ")";
						}
					}
				}
				if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schUsed"))))) {
					str += "  AND A.USED = '" + key.get("schUsed") + "'";
					
				}
				if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schApvStat"))))) {
					if (key.get("schApvStat") == "2") {
						str += "  AND EXISTS (" + "                               SELECT 'X'"
								+ "                                 FROM NHAPR100 X"
								+ "                                WHERE A.ACEPNO = X.APPL_ID"
								+ "                                  AND X.STAT_CD IN ('T', 'P', 'R')"
								+ "                              )";
					}
					if (key.get("schApvStat") == "1") {
						str += "  AND EXISTS (" + "                             SELECT 'X'"
								+ "                               FROM ("
								+ "                                     SELECT X.APPL_ID, MAX(DECODE(STAT_CD, 'Y', '1', 'T', '2', 'P', '2', 'R', '2', '0')) AS STAT_CD"
								+ "                                       FROM NHAPR100 X"
								+ "                                      GROUP BY X.APPL_ID"
								+ "                                    ) Y"
								+ "                              WHERE A.ACEPNO = Y.APPL_ID"
								+ "                                AND Y.STAT_CD = '1'"
								+ "                              )";
					}
				}
				if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schProdCd"))))) {
					str += "  AND A.PROD_CD  IN ( " + key.get("schProdCd") + " )";
					str += "  AND A.PROD_CD IN ( DECODE(INSTR(" + key.get("schProdCd") +", ','), 1, CONCAT(CONCAT(REGEXP_SUBSTR( " + key.get("schProdCd") +",'[^,]+', 1, 1), ','), REGEXP_SUBSTR( "+key.get("schProdCd")+",'[^,]+', 1, 2))  , REGEXP_SUBSTR("+key.get("schProdCd")+",'[^,]+', 1, 1) ) "+ ")";  
				}
				if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schLcls"))))) {
					str += "  AND A.PROD_CD  IN"
							+ "	               (                                                                                                                   "
							+ "              			SELECT DISTINCT PROD_CODE                                                                                   "
							+ "   					  	FROM SDSTT301 S31                                                                 "
							+ "   					       , T179T SAP                                                                 "
							+ "                        WHERE 1=1                                                                                              "
							+ "                        AND S31.BOSS_PROD_CODE = SAP.PRODH ";
					if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schLcls"))))) {
						str += " AND S31.PRTY_1_CODE = '" + key.get("schLcls") + "' ";
					}
					if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schMcls"))))) {
						str += " AND S31.PRTY_2_CODE = '" + key.get("schMcls") + "' ";
					}
					if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schScls"))))) {
						str += " AND S31.PRTY_3_CODE = '" + key.get("schScls") + "' ";
					}
					if (!"".equals(StringUtil.trim(StringUtil.null2void(key.get("schBossCd"))))) {
						str += " AND S31.BOSS_PROD_CODE = '" + key.get("schBossCd") + "' ";
					}
					if (!"".equals(StringUtil.trim(String.valueOf(key.get("schProdCd"))))
							&& key.get("schProdCd") != "null"
							&& key.get("schProdCd") != null
							&& key.get("schProdCd") != ",") {
						str += " AND S31.PROD_CODE IN ( DECODE(INSTR(" + key.get("schProdCd") +", ','), 1, CONCAT(CONCAT(REGEXP_SUBSTR( " + key.get("schProdCd") +",'[^,]+', 1, 1), ','), REGEXP_SUBSTR( "+key.get("schProdCd")+",'[^,]+', 1, 2))  , REGEXP_SUBSTR("+key.get("schProdCd")+",'[^,]+', 1, 1) ) "+ ")";  
					}
					str += ")";
				}
					str += "  ORDER BY " + key.get("sidx") + "    ";
					if (!"".equals(StringUtil.trim(String.valueOf(key.get("sord")))) && key.get("sord") != "null"  && key.get("sord") != null) {
						str += " " + key.get("sord") + "     ";
					}
					str += "            ) aa " + "    		where 1=1 ";
					if (key.get("schEmergency_yn") != "" && key.get("schEmergency_yn") != "null") {
						str += " AND aa.emergency_yn = 'Y' ";
					}
					if (key.get("schMaxreturn_yn") != "" && key.get("schMaxreturn_yn") != "null") {
						str += " AND aa.maxreturn_yn = 'Y' ";
					}
				
				str += "  ) A " + "          WHERE 1=1  ";
				str += "  ) A " + "          WHERE 1=1  ";
				str += " AND RNUM >= " + startNo + " AND RNUM <= " + endNo + "";
				break;
			}
			}
			this.strQuery = str;
		}

		@Override
		public void run() {
			try {
				Connection conn = null;
				ResultSet rs = null;
				PreparedStatement stmt = null;
				try {
					conn = SYSTEM.getInstance().getConnection();
					stmt = conn.prepareStatement(strQuery);
					rs = stmt.executeQuery();
					ResultSetMetaData md = rs.getMetaData();
					int columns = md.getColumnCount();
					while (rs.next()) {
						HashMap<String, Object> row = new HashMap<String, Object>(columns);
						for (int i = 1; i <= columns; ++i) {
							row.put(md.getColumnName(i), rs.getObject(i));
						}
						addList(row);
					}
				} catch (SQLException e) {

				} finally {
					if (stmt != null)
						try {
							stmt.close();
						} catch (SQLException logOrIgnore) {
						}
					if (conn != null)
						try {
							conn.close();
							rs.close();
						} catch (SQLException logOrIgnore) {
						}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}