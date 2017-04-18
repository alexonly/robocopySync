import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.sql.*;

class SendCounter {
	public static void main(String[] args) {
		boolean blnIsException = false;

		//---<---���------
		Calendar today = Calendar.getInstance();
		long lngYesterday = today.getTimeInMillis()-86400000;
		today.setTimeInMillis(lngYesterday);
		int intyear = today.get(Calendar.YEAR);
		int intmonth = today.get(Calendar.MONTH)+1;
		int intday = today.get(Calendar.DATE);
		String strDate = String.valueOf(intyear)+String.valueOf(intmonth)+String.valueOf(intday);
		String strDateOutput = String.valueOf(intyear) + " / " + String.valueOf(intmonth) + " / " + String.valueOf(intday);
		String strDateOutput2 = String.valueOf(intyear) + " / " + String.valueOf(intmonth);
		
		//����SQL�y�k�B���o�έp��ơB�̫��i�H�󥻤�
		
        String strTotalCount = "";	//����@�زz�M�`�H��
        
		String Login_Times = "";	//�ϥΪ̵n�J�H��
        String Login_People = "";	//�ϥΪ̵n�J�H��
        
		String SACM_Times = "";		//��ƻ`����J�ϥΤH��
		String SACM_People = "";	//��ƻ`����J�ϥΤH��
		
		String SAPC_Times = "";		//�O��ե��ϥΤH��
		String SAPC_People = "";	//�O��ե��ϥΤH��
		
		String SAPP_Times = "";		//�ӫ~��ĳ�ѨϥΤH��
		String SAPP_People = "";	//�ӫ~��ĳ�ѨϥΤH��
		
        String SAPD_Times = "";		//������s������O�t�ΨϥΤH��
        String SAPD_People = "";	//������s������O�t�ΨϥΤH��
        
		String SENC_Times = "";		//�s�����A�ȨϥΤH��
		String SENC_People = "";	//�s�����A�ȨϥΤH��
		
		String SEAS_Times = "";		//�z�ߨϥΤH��
		String SEAS_People = "";	//�z�ߨϥΤH��
		
		String SEBF_Times = "";		//�O�O�ϥΤH��
		String SEBF_People = "";	//�O�O�ϥΤH��
		
		String SEBI_Times = "";		//�O���ϥΤH��
		String SEBI_People = "";	//�O���ϥΤH��
		
        String SEDL_Times = "";		//���U���ϥΤH��
        String SEDL_People = "";	//���U���ϥΤH��
		
        String SEQD_Times = "";		//��ꫬ�ӫ~�M�ϨϥΤH��
        String SEQD_People = "";	//��ꫬ�ӫ~�M�ϨϥΤH��
		
		Vector vtFirstTen = new Vector();//�ϥβv�ƦW�e�Q�W
		
        String strTodayCustomer = "" ;  //���s�W�Ȥ��
        String strTotalCustomer = "" ;  //���Z�Ȥ��
		
		ArrayList mailList = new ArrayList();//20100310�ק�Ѹ�Ʈw��X����̸��
		
        //---<---get db data------
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch (Exception e1) {
			System.out.println(e1.toString());
			blnIsException = true;
		}

		Connection objConn = null;

		try {
			//20100310�ק�n�O�o��^��
            objConn = DriverManager.getConnection("jdbc:odbc:BankCRM");
			Calendar objCal1 = Calendar.getInstance();
			Calendar objCal2 = Calendar.getInstance();
			objCal1.add(Calendar.DATE, -1);
            //�έp��0��0�I0��
			String strPrintBeginTime = objCal1.get(Calendar.YEAR) + "-" + (objCal1.get(Calendar.MONTH) + 1) + "-" + objCal1.get(Calendar.DATE) + " 00:00:00";
            //�έp���@��0��0�I0��
			String strPrintEndTime = objCal2.get(Calendar.YEAR) + "-" + (objCal2.get(Calendar.MONTH) + 1) + "-" + objCal2.get(Calendar.DATE) + " 00:00:00";
	        //�έp����1��
			String strBeginTime = objCal1.get(Calendar.YEAR) + "-" + (objCal1.get(Calendar.MONTH) + 1) + "-1 00:00:00";			
			//�έp��
			String strBeginDate = objCal1.get(Calendar.YEAR) + "-" + (objCal1.get(Calendar.MONTH) + 1) + "-" + objCal1.get(Calendar.DATE) ;

            //�p���`�H�ơB�`�H��
            PreparedStatement objPStmt = objConn.prepareStatement(" SELECT count(a.USER_ID),count(distinct a.USER_ID) FROM BANKUSER.USER_ACT_LOG a LEFT JOIN BANKUSER.UWCEMP b on a.USER_ID = b.AGID WHERE b.BT_NOTE in ('FA1','FA2','FB1','FB2','PFB') AND b.STATUS = 1 AND a.ACT_TIME BETWEEN '" + strPrintBeginTime + "' and '" + strPrintEndTime + "' AND a.FUNC_ID = 'LOGIN' ");

			ResultSet objRS = objPStmt.executeQuery();

			while (objRS.next()) {
				Login_Times = objRS.getString(1);
				Login_People = objRS.getString(2);
			}
			
			//����@�زz�M�`�H��
			objPStmt = objConn.prepareStatement(" SELECT count(AGID) FROM BANKUSER.UWCEMP WHERE BT_NOTE in ('FA1','FA2','FB1','FB2','PFB') and STATUS = 1 ");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				strTotalCount = objRS.getString(1);
			}
			
			//��ƻ`����J
			objPStmt = objConn.prepareStatement(" SELECT count(a.USER_ID),count(distinct a.USER_ID) FROM BANKUSER.USER_ACT_LOG a LEFT JOIN BANKUSER.UWCEMP b on a.USER_ID = b.AGID WHERE b.BT_NOTE in ('FA1','FA2','FB1','FB2','PFB') AND b.STATUS = 1 AND a.ACT_TIME BETWEEN '" + strPrintBeginTime + "' and '" + strPrintEndTime + "' AND a.FUNC_ID = 'SACM0100' ");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				SACM_Times = objRS.getString(1);
				SACM_People = objRS.getString(2);
			}
			
			//�O��ե�
			objPStmt = objConn.prepareStatement(" SELECT count(a.USER_ID),count(distinct a.USER_ID) FROM BANKUSER.USER_ACT_LOG a LEFT JOIN BANKUSER.UWCEMP b on a.USER_ID = b.AGID WHERE b.BT_NOTE in ('FA1','FA2','FB1','FB2','PFB') AND b.STATUS = 1 AND a.ACT_TIME BETWEEN '" + strPrintBeginTime + "' and '" + strPrintEndTime + "' AND a.FUNC_ID = 'SAPC0100' ");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				SAPC_Times = objRS.getString(1);
				SAPC_People = objRS.getString(2);
			}
			
			//�ӫ~��ĳ��
			objPStmt = objConn.prepareStatement(" SELECT count(a.USER_ID),count(distinct a.USER_ID) FROM BANKUSER.USER_ACT_LOG a LEFT JOIN BANKUSER.UWCEMP b on a.USER_ID = b.AGID WHERE b.BT_NOTE in ('FA1','FA2','FB1','FB2','PFB') AND b.STATUS = 1 AND a.ACT_TIME BETWEEN '" + strPrintBeginTime + "' and '" + strPrintEndTime + "' AND a.FUNC_ID = 'SAPP0100' ");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				SAPP_Times = objRS.getString(1);
				SAPP_People = objRS.getString(2);
			}
			
			//������s������O�t��
			objPStmt = objConn.prepareStatement(" SELECT count(a.USER_ID),count(distinct a.USER_ID) FROM BANKUSER.USER_ACT_LOG a LEFT JOIN BANKUSER.UWCEMP b on a.USER_ID = b.AGID WHERE b.BT_NOTE in ('FA1','FA2','FB1','FB2','PFB') AND b.STATUS = 1 AND a.ACT_TIME BETWEEN '" + strPrintBeginTime + "' and '" + strPrintEndTime + "' AND a.FUNC_ID = 'SAPD0100' ");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				SAPD_Times = objRS.getString(1);
				SAPD_People = objRS.getString(2);
			}
			
			//�s�����A��
			objPStmt = objConn.prepareStatement(" SELECT count(a.USER_ID),count(distinct a.USER_ID) FROM BANKUSER.USER_ACT_LOG a LEFT JOIN BANKUSER.UWCEMP b on a.USER_ID = b.AGID WHERE b.BT_NOTE in ('FA1','FA2','FB1','FB2','PFB') AND b.STATUS = 1 AND a.ACT_TIME BETWEEN '" + strPrintBeginTime + "' and '" + strPrintEndTime + "' AND (a.FUNC_ID like 'SENC%' OR a.FUNC_ID in ('SEBI1200','SEBI0100','SEBI0200','SEBI0300')) ");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				SENC_Times = objRS.getString(1);
				SENC_People = objRS.getString(2);
			}
			
			//�z��
			objPStmt = objConn.prepareStatement(" SELECT count(a.USER_ID),count(distinct a.USER_ID) FROM BANKUSER.USER_ACT_LOG a LEFT JOIN BANKUSER.UWCEMP b on a.USER_ID = b.AGID WHERE b.BT_NOTE in ('FA1','FA2','FB1','FB2','PFB') AND b.STATUS = 1 AND a.ACT_TIME BETWEEN '" + strPrintBeginTime + "' and '" + strPrintEndTime + "' AND (a.FUNC_ID like 'SEAS%' OR a.FUNC_ID = 'SEEO0100') ");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				SEAS_Times = objRS.getString(1);
				SEAS_People = objRS.getString(2);
			}
			
			//�O�O
			objPStmt = objConn.prepareStatement(" SELECT count(a.USER_ID),count(distinct a.USER_ID) FROM BANKUSER.USER_ACT_LOG a LEFT JOIN BANKUSER.UWCEMP b on a.USER_ID = b.AGID WHERE b.BT_NOTE in ('FA1','FA2','FB1','FB2','PFB') AND b.STATUS = 1 AND a.ACT_TIME BETWEEN '" + strPrintBeginTime + "' and '" + strPrintEndTime + "' AND (a.FUNC_ID like 'SEBF%' OR a.FUNC_ID in ('SEBI0400','SEBI0500','SEBI0800','SEBI1000','ADCQ0600')) ");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				SEBF_Times = objRS.getString(1);
				SEBF_People = objRS.getString(2);
			}
			
			//�O��
			objPStmt = objConn.prepareStatement(" SELECT count(a.USER_ID),count(distinct a.USER_ID) FROM BANKUSER.USER_ACT_LOG a LEFT JOIN BANKUSER.UWCEMP b on a.USER_ID = b.AGID WHERE b.BT_NOTE in ('FA1','FA2','FB1','FB2','PFB') AND b.STATUS = 1 AND a.ACT_TIME BETWEEN '" + strPrintBeginTime + "' and '" + strPrintEndTime + "' AND a.FUNC_ID in ('SEBI0600','SEBI0700','SEBI0900','SEBI1100') ");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				SEBI_Times = objRS.getString(1);
				SEBI_People = objRS.getString(2);
			}
			
			//���U��
			objPStmt = objConn.prepareStatement(" SELECT count(a.USER_ID),count(distinct a.USER_ID) FROM BANKUSER.USER_ACT_LOG a LEFT JOIN BANKUSER.UWCEMP b on a.USER_ID = b.AGID WHERE b.BT_NOTE in ('FA1','FA2','FB1','FB2','PFB') AND b.STATUS = 1 AND a.ACT_TIME BETWEEN '" + strPrintBeginTime + "' and '" + strPrintEndTime + "' AND a.FUNC_ID like 'SEDL%' ");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				SEDL_Times = objRS.getString(1);
				SEDL_People = objRS.getString(2);
			}
			
			//��ꫬ�ӫ~�M��
			objPStmt = objConn.prepareStatement(" SELECT count(a.USER_ID),count(distinct a.USER_ID) FROM BANKUSER.USER_ACT_LOG a LEFT JOIN BANKUSER.UWCEMP b on a.USER_ID = b.AGID WHERE b.BT_NOTE in ('FA1','FA2','FB1','FB2','PFB') AND b.STATUS = 1 AND a.ACT_TIME BETWEEN '" + strPrintBeginTime + "' and '" + strPrintEndTime + "' AND a.FUNC_ID like 'SEQD%' ");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				SEQD_Times = objRS.getString(1);
				SEQD_People = objRS.getString(2);
			}
			
			//���s�W���Ȥ�
			objPStmt = objConn.prepareStatement(" SELECT count(a.CSIDNO) FROM BANKUSER.O_CUSTOMER a LEFT JOIN BANKUSER.UWCEMP b on a.AGIDNO = b.AGID WHERE b.BT_NOTE in ('FA1','FA2','FB1','FB2','PFB') AND b.STATUS = 1 AND a.INDATE = '"+ strBeginDate +"' ");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				strTodayCustomer = objRS.getString(1);
			}
			
            //�ֿn�s�W���Ȥ�
			objPStmt = objConn.prepareStatement(" SELECT count(a.CSIDNO) FROM BANKUSER.O_CUSTOMER a LEFT JOIN BANKUSER.UWCEMP b on a.AGIDNO = b.AGID WHERE b.BT_NOTE in ('FA1','FA2','FB1','FB2','PFB') AND b.STATUS = 1 AND a.AGIDNO NOT IN (SELECT USER_ID FROM BANKUSER.USER WHERE CEBANKNO = 'CXL90' ) ");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				strTotalCustomer = objRS.getString(1);
			}
			
			//���ϥΦ��ƫe�Q�W
			String strSQLFirst = " select z.FUNC_ID,y.FUNC_NAME,x.FUNC_NAME,count(*) "
            + " from "
            + " ( "
            + " select a.USER_ID,substr(a.FUNC_ID,1,4) || '0000' LEVEL2_ID,a.FUNC_ID "
            + " from BANKUSER.USER_ACT_LOG a "
            + " left join BANKUSER.UWCEMP b on a.USER_ID = b.AGID "
            + " where b.BT_NOTE in ('FA1','FA2','FB1','FB2','PFB') and b.STATUS = 1 "
            + " and a.ACT_TIME > '" + strBeginTime + "'"
            + " and a.FUNC_ID not in ('LOGIN','ILHP0100') "
            + " ) z ,BANKUSER.FUNCTION x,BANKUSER.FUNCTION y "
            + " where z.FUNC_ID = x.FUNC_ID and z.LEVEL2_ID = y.FUNC_ID "
            + " group by z.FUNC_ID,y.FUNC_NAME,x.FUNC_NAME "
            + " order by count(*) desc fetch first 10 rows only";
			
            objPStmt = objConn.prepareStatement(strSQLFirst);
            objRS = objPStmt.executeQuery();
            while (objRS.next()) {
                vtFirstTen.add(objRS.getString(3)+"<td align='center' class='ad'>"+objRS.getString(4));
            }
            
            objPStmt = objConn.prepareStatement("select RC_NAME,RC_MAIL from INSDB.DAILYSTATS_RECEIVER where dailyx = '�ȫO�M��'");
            objRS = objPStmt.executeQuery();
            while (objRS.next()) {
				String[] address = {objRS.getString(1),objRS.getString(2)};
				mailList.add(address);
            }
            
			objRS.close();
			objPStmt.close();
			objConn.close();
			objPStmt = null;
			objRS = null;
			objConn = null;
		}
		
		catch (Exception e) {
			//---<---log------
			writeLog(strDateOutput + ": get db counter's exception: " + e.toString());
			//------log--->---
			blnIsException = true;
		}
		
		finally {
			try {
				if (objConn != null) {
					objConn.close();
					objConn = null;
				}
			}
			catch (Exception e3) {
				System.out.println(e3.toString());
				blnIsException = true;
			}
		}

		//------get db data--->---

		String mailserver   = "sendmail.cathaylife.com.tw";
		String From         = "cathaylife_banc@cathlife.com.tw";
		String Subject      = "����H�ػȫO�M�ϨϥΪ��p";

		StringBuffer sb = new StringBuffer();

	try {
		
		sb.append("<HTML><HEAD><meta http-equiv='Content-Type' content='text/html; charset=BIG5'><title>����H�� �ȫO�M��</title>");
		sb.append("<style type='text/css'>");
		sb.append("	.sa { background-color:#FFe8e8} .ad { background-color:#ffefdf} .se { background-color:#e8f3ff} ");
		sb.append("</style>");
		sb.append("</head>");
		sb.append("<body bgcolor='FFFFFF'><center>");
		sb.append("<table bgcolor='FFFFFF' width='70%' border='1'>");
		
		sb.append("<tr bgcolor='E9F7F0'>");
		sb.append("<td colspan='4' align='center'>�m " + strDateOutput + " ����H�ػȫO�M�� �ϥΪ��p ( �έp�ɶ��G�� ) �n");
		
		sb.append("<tr valign='middle'>");
		sb.append("<td colspan='2' width='40%' align='center'>\\</td>");
		sb.append("<td width='30%' align='center'>�z�M�`�H��<br>( " + strTotalCount + " �H )");
		sb.append("<td width='30%' align='center'>�`�H��");
		
		sb.append("<tr>");
		sb.append("<td colspan='2' align='center'>����H�ػȫO�M�Ϩϥζq");
		sb.append("<td align='center'>" + Login_People + " �H ");
		sb.append("<td align='center'>" + Login_Times + " �H ");
		
		//*********** ��P�䴩 *****************************
		sb.append("<tr>");
		sb.append("<td align='center' rowspan='4' width='12%' class='sa'>��P�䴩");
		sb.append("<td class='sa'>��ƻ`����J");
		sb.append("<td align='center' class='sa'>" + SACM_People);
		sb.append("<td align='center' class='sa'>" + SACM_Times);
		
		sb.append("<tr>");
		sb.append("<td class='sa'>�O��ե�");
		sb.append("<td align='center' class='sa'>" + SAPC_People);
		sb.append("<td align='center' class='sa'>" + SAPC_Times);
		
		sb.append("<tr>");
		sb.append("<td class='sa'>�ӫ~��ĳ��");
		sb.append("<td align='center' class='sa'>" + SAPP_People);
		sb.append("<td align='center' class='sa'>" + SAPP_Times);
		
		sb.append("<tr>");
		sb.append("<td class='sa'>������s������O�t��");
		sb.append("<td align='center' class='sa'>" + SAPD_People);
		sb.append("<td align='center' class='sa'>" + SAPD_Times);
		
		//*********** �A�Ȥ䴩 *****************************
		sb.append("<tr>");
		sb.append("<td align='center' rowspan='6' width='12%' class='se'>�A�Ȥ䴩");
		sb.append("<td class='se'>�s�����A��");
		sb.append("<td align='center' class='se'>" + SENC_People);
		sb.append("<td align='center' class='se'>" + SENC_Times);

		sb.append("<tr>");
		sb.append("<td class='se'>�z��");
		sb.append("<td align='center' class='se'>" + SEAS_People);
		sb.append("<td align='center' class='se'>" + SEAS_Times);

		sb.append("<tr>");
		sb.append("<td class='se'>�O�O");
		sb.append("<td align='center' class='se'>" + SEBF_People);
		sb.append("<td align='center' class='se'>" + SEBF_Times);

		sb.append("<tr>");
		sb.append("<td class='se'>�O��");
		sb.append("<td align='center' class='se'>" + SEBI_People);
		sb.append("<td align='center' class='se'>" + SEBI_Times);

		sb.append("<tr>");
		sb.append("<td class='se'>���U��");
		sb.append("<td align='center' class='se'>" + SEDL_People);
		sb.append("<td align='center' class='se'>" + SEDL_Times);
		
		sb.append("<tr>");
		sb.append("<td class='se'>��ꫬ�ӫ~�M��");
		sb.append("<td align='center' class='se'>" + SEQD_People);
		sb.append("<td align='center' class='se'>" + SEQD_Times);
		
		//***********�s�W�Ȥ���*****************************
		sb.append("<tr>");
		sb.append("<td rowspan='3' colspan='2' align='center'>�s�W�Ȥ���");
		sb.append("<td align='center'>���s�W ");
		sb.append("<td align='center'>�ֿn�s�W ");
		
		sb.append("<tr>");
		sb.append("<td align='center'>" + strTodayCustomer + " �H ");
		sb.append("<td align='center'>" + strTotalCustomer + " �H ");
		sb.append("</table>");

        if (vtFirstTen.size()>0) {
        	
            sb.append("	<p><p><hr><p><p> ");
            
            //*********** ���ϥΦ��ƫe�Q�W *****************************
    		sb.append("<table bgcolor='FFFFFF' width='70%' border='1'>");
    		sb.append("<tr bgcolor='E9F7F0'>");
    		sb.append("<td colspan='2' align='center'>�m " + strDateOutput2 + " ����H�ػȫO�M�� �ϥΥ\��Ʀ�] ( �έp�ɶ��G�� ) �n");
    		sb.append("<tr>");
    		sb.append("<td colspan='2' align='center'>�m ���ϥΦ��ƫe�Q�W �n");
    		for(int i=0 ; i<10 ; i++) {
                sb.append("<tr>");
                if(vtFirstTen.size()<=i) {
                    sb.append("<td width='50%' align='center' class='ad'>");
                } else {
                    sb.append("<td width='50%' align='center' class='ad'>" + vtFirstTen.get(i));
                }
            }
        }
        sb.append("</table></center></body></html>");
        
        String messageText = sb.toString();
        
		boolean sessionDebug = false;
		InternetAddress[] toAddress = new InternetAddress[mailList.size()];

		for(int i=0;i<mailList.size();i++){
			String[] address =(String[])mailList.get(i);
			toAddress[i]=new InternetAddress(address[1].toLowerCase(),address[0],"big5");
		}
		
			// set properties...
			Properties props = System.getProperties();
			props.put("mail.host", mailserver);
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.auth", "true");

			// new Session service...
			javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(props, new SMTPAuthenticator(From, "crmbank2"));

			mailSession.setDebug(sessionDebug);
			
			
			// new Message...
			MimeMessage msg = new MimeMessage(mailSession);

			// set sender...
			msg.setFrom(new InternetAddress(From,"����H�� �ȫO�M��","big5"));

			// set recipient...
			//address = InternetAddress.parse(to,false);


			msg.setRecipients(Message.RecipientType.TO, toAddress);

			// set Subject...
			msg.setSubject(Subject,"big5");

			// set SendDate...
			msg.setSentDate(new java.util.Date());

			// set Content...
			// msg.setText(messageText);
			 msg.setContent(messageText, "text/html; charset=Big5");      // �H HTML �o�e

			// send Message...

			Transport.send(msg);
		}
		catch (Exception e) {
			//---<---log------
			writeLog(strDateOutput + ": email's exception: " + e.toString());
			//------log--->---
			blnIsException = true;
		}

		//---<---log------
		if (blnIsException == true) {
			writeLog(strDateOutput + ": exception...");
		}
		else {
			writeLog(strDateOutput + ": successful...");
		}

		//------log--->---

	}

	private static void writeLog(String str) {
		try {
			FileWriter fw = new FileWriter("SendCounter.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(str);
			bw.newLine();
			bw.close();
			fw.close();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
