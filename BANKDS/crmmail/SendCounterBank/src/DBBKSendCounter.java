import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.sql.*;

class DBBKSendCounter {
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
		String strDateOutput = String.valueOf(intyear) + "/" + String.valueOf(intmonth) + "/" + String.valueOf(intday);

		//20100310�ק�A��Ѹ�ƮwINSDB.DAILYSTATS_RECEIVER����X�����
		//����SQL�y�k�B���o�έp��ơB�̫��i�H�󥻤�

        String strTotalCount = "";     //�D�����ȫO�M���`�H��

		String strLoginCountTimes = "" ;  //�D�����ȫO�M�ϨϥΪ̵n�J�H��
		String strLoginCountPeople = "" ;  //�D�����ȫO�M�ϨϥΪ̵n�J�H��

		String strProcessingCountTimes = "" ;  //���z�i�׬d�ߨϥΤH��
		String strProcessingCountPeople = "" ;  //���z�i�׬d�ߨϥΤH��

		String strPolicyMailCountTimes = "" ;  //�O��H�e�d�ߨϥΤH��
		String strPolicyMailCountPeople = "" ;  //�O��H�e�d�ߨϥΤH��

		String strPolicyValueCountTimes = "" ;  //�O��b����Ȭd�ߨϥΤH��
		String strPolicyValueCountPeople = "" ;  //�O��b����Ȭd�ߨϥΤH��

        String strExchangeRateCountTimes = "" ;  //�ײv�d�ߨϥΤH��
        String strExchangeRateCountPeople = "" ;  //�ײv�d�ߨϥΤH��
        
        Vector vtRegistedPeople = new Vector();//���U�H��
		
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
		Connection objConn1 = null;

		try {
			//20100310�ק�n�O�o��^��
            objConn = DriverManager.getConnection("jdbc:odbc:DBBK");
			objConn1 = DriverManager.getConnection("jdbc:odbc:BankCRM");
			Calendar objCal1 = Calendar.getInstance();
			Calendar objCal2 = Calendar.getInstance();
			objCal1.add(Calendar.DATE, -1);
            //�έp��0��0�I0��
			String strPrintBeginTime = objCal1.get(Calendar.YEAR) + "-" + (objCal1.get(Calendar.MONTH) + 1) + "-" + objCal1.get(Calendar.DATE) + " 00:00:00";
            //�έp���@��0��0�I0��
			String strPrintEndTime = objCal2.get(Calendar.YEAR) + "-" + (objCal2.get(Calendar.MONTH) + 1) + "-" + objCal2.get(Calendar.DATE) + " 00:00:00";
            //�έp��
			String strBeginDate = objCal1.get(Calendar.YEAR) + "-" + (objCal1.get(Calendar.MONTH) + 1) + "-" + objCal1.get(Calendar.DATE) ;

            //�p���`�H�ơB�`�H��
            PreparedStatement objPStmt = objConn.prepareStatement(" SELECT count(*),count(distinct USER_ID) FROM DBBK.CTBK_LOG WHERE USER_DEPTNAME not in ('','�D���@��','�D���G��','��P��T��','���{�]��','�q����T��','��T�]�ư�','���I��','���I��T�@','���I��T�T') AND LOG_TIME BETWEEN '" + strPrintBeginTime + "' and '" + strPrintEndTime + "' and OP_FUNCTION='doPrompt' ");

			ResultSet objRS = objPStmt.executeQuery();

			while (objRS.next()) {
				strLoginCountTimes = objRS.getString(1);
				//System.out.println("����"+strLoginCountTimes);
				strLoginCountPeople = objRS.getString(2);
			}

			//�D�����ȫO�M���`�H��
			objPStmt = objConn.prepareStatement(" SELECT count(distinct USER_ID) FROM DBBK.CTBK_LOG where USER_DEPTNAME not in ('','�D���@��','�D���G��','��P��T��','���{�]��','�q����T��','��T�]�ư�','���I��','���I��T�@','���I��T�T') ");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				strTotalCount = objRS.getString(1);
			}


			//---<---���z�i�׬d��------
			objPStmt = objConn.prepareStatement(" SELECT count(*),count(distinct USER_ID) FROM DBBK.CTBK_LOG WHERE USER_DEPTNAME not in ('','�D���@��','�D���G��','��P��T��','���{�]��','�q����T��','��T�]�ư�','���I��','���I��T�@','���I��T�T') AND LOG_TIME BETWEEN '" + strPrintBeginTime + "' and '" + strPrintEndTime + "' AND OP_CLASS like 'BKPL_01%' and OP_FUNCTION='doPrompt' ");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				strProcessingCountTimes = objRS.getString(1);
				strProcessingCountPeople = objRS.getString(2);
			}
			//------���z�i�׬d��--->---


			//---<---�O��H�e�d��------
			objPStmt = objConn.prepareStatement(" SELECT count(*),count(distinct USER_ID) FROM DBBK.CTBK_LOG WHERE USER_DEPTNAME not in ('','�D���@��','�D���G��','��P��T��','���{�]��','�q����T��','��T�]�ư�','���I��','���I��T�@','���I��T�T') AND LOG_TIME BETWEEN '" + strPrintBeginTime + "' and '" + strPrintEndTime + "' AND OP_CLASS like 'BKPL_02%' and OP_FUNCTION='doPrompt' ");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				strPolicyMailCountTimes = objRS.getString(1);
				strPolicyMailCountPeople = objRS.getString(2);
			}
			//------�O��H�e�d��--->---


			//---<---�O��b����Ȭd��------
			objPStmt = objConn.prepareStatement(" SELECT count(*),count(distinct USER_ID) FROM DBBK.CTBK_LOG WHERE USER_DEPTNAME not in ('','�D���@��','�D���G��','��P��T��','���{�]��','�q����T��','��T�]�ư�','���I��','���I��T�@','���I��T�T') AND LOG_TIME BETWEEN '" + strPrintBeginTime + "' and '" + strPrintEndTime + "' AND OP_CLASS like 'BKPL_03%' and OP_FUNCTION='doPrompt' ");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				strPolicyValueCountTimes = objRS.getString(1);
				strPolicyValueCountPeople = objRS.getString(2);
			}
			//------�O��b����Ȭd��--->---


			//---<---�ײv�d��------
			objPStmt = objConn.prepareStatement(" SELECT count(*),count(distinct USER_ID) FROM DBBK.CTBK_LOG WHERE USER_DEPTNAME not in ('','�D���@��','�D���G��','��P��T��','���{�]��','�q����T��','��T�]�ư�','���I��','���I��T�@','���I��T�T') AND LOG_TIME BETWEEN '" + strPrintBeginTime + "' and '" + strPrintEndTime + "' AND OP_CLASS like 'BKPD_02%' and OP_FUNCTION='doPrompt'  ");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				strExchangeRateCountTimes = objRS.getString(1);
				strExchangeRateCountPeople = objRS.getString(2);
			}
			//------�ײv�d��--->---
			
			
			//---<---���U�H��------
			objPStmt = objConn.prepareStatement(" SELECT USER_DEPTNAME as ���W��,count(distinct( USER_ID)) as ���U�H�� FROM DBBK.CTBK_LOG WHERE USER_DEPTNAME not in ('','�D���@��','�D���G��','��P��T��','���{�]��','�q����T��','��T�]�ư�','���I��','���I��T�@','���I��T�T') GROUP BY USER_DEPTNAME order by ���U�H�� desc  ");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				vtRegistedPeople.add(objRS.getString(1)+"<td align=\"center\">"+objRS.getString(2));
			}
			//------���U�H��--->---

			
			//20100310�ק�Ѹ�ƮwŪ����̸��
            objPStmt = objConn1.prepareStatement("select RC_NAME,RC_MAIL from INSDB.DAILYSTATS_RECEIVER where dailyx = '�D�����ȫO�M��'");
            objRS = objPStmt.executeQuery();
            while (objRS.next()) {
				String[] address = {objRS.getString(1),objRS.getString(2)};
				mailList.add(address);
                //System.out.println(address[0]+address[1]);
            }//20100310

			objRS.close();
			objPStmt.close();
			objConn.close();
			objConn1.close();
			objPStmt = null;
			objRS = null;
			objConn = null;
			objConn1 = null;
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
				if (objConn1 != null) {
					objConn1.close();
					objConn1 = null;
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
		String Subject      = "����H�ثD�����ȫO�M�ϨϥΪ��p";


		StringBuffer sb = new StringBuffer();

	try {

		sb.append("<html><meta http-equiv=\"Content-Type\" content=\"text/html; charset=BIG5\"><title>����H�� �D�����ȫO�M��</title>");
		sb.append("<style type='text/css'>");
		sb.append("	.sa { background-color:#FFe8e8} .ad { background-color:#ffefdf} .se { background-color:#e8f3ff} ");
		sb.append("</style>");
		sb.append("</head>");
		sb.append("<body bgcolor=\"FFFFFF\"><center>    ");
		sb.append("<table bgcolor=\"FFFFFF\" width=\"70%\" border=\"1\">   ");

		sb.append("<tr bgcolor=\"E9F7F0\" >  ");
		sb.append("	<td colspan=\"4\" align=\"center\"> <<  " + strDateOutput + " �D�����ȫO�M�� �ϥΪ��p >>");

		sb.append("<tr valign=\"middle\">		         ");
		sb.append("	<td colspan='2' width=\"40%\" align=\"center\">\\</td>       ");
		sb.append(" 	<td align=\"center\">�`�H��<br>("+strTotalCount+"�H)  ");
		sb.append("   <td align=\"center\">�`�H��     ");
		sb.append("<tr>		         ");
		sb.append("	<td colspan='2' align='center' >�D�����ȫO�M�Ϩϥζq");
		sb.append(" 	<td align=\"center\">"+strLoginCountPeople+" ");
		sb.append("  <td align=\"center\">"+strLoginCountTimes+"  ");
		sb.append("<tr>		         ");
		sb.append("	<td  rowspan='3' width=\"8%\" class='sa'>  �O��޲z      ");		
		sb.append("	<td class='sa'>���z�i�׬d��");
		sb.append(" 	<td align=\"center\" class='sa'>"+strProcessingCountPeople+"  ");
		sb.append("  <td align=\"center\" class='sa'>"+strProcessingCountTimes+"  ");
		sb.append("<tr>		         ");
		sb.append("	<td class='sa'>�O��H�e�d��");
		sb.append(" 	<td align=\"center\" class='sa'>"+strPolicyMailCountPeople+"  ");
		sb.append("  <td align=\"center\" class='sa'>"+strPolicyMailCountTimes+"   ");
		sb.append("<tr>		         ");
		sb.append("	<td class='sa'>�O��b����Ȭd��        ");
		sb.append(" 	<td align=\"center\" class='sa'>"+strPolicyValueCountPeople+"  ");
		sb.append("  <td align=\"center\" class='sa'>"+strPolicyValueCountTimes+"   ");


		//***********�ײv�d��*****************************
        sb.append("<tr>		         ");
        sb.append("	<td class='se'>  �ײv�d��   	");
		sb.append("	<td class='se'>�ײv�d��         ");
		sb.append(" 	<td align=\"center\" class='se'>"+strExchangeRateCountPeople+"  ");
		sb.append("  <td align=\"center\" class='se'>"+strExchangeRateCountTimes+"   ");
		sb.append("</table>     ");
		
		//*****************���U�H��*****************
		sb.append(" <table bgcolor='FFFFFF' width=\"60%\" border='1'> ");

		sb.append("<tr bgcolor=\"E9F7F0\" >  ");
		sb.append("	<td colspan=\"2\" align=\"center\"> << �D�����ȫO�M�� ���U�H�� >>");		
		for(int i=0 ; i<vtRegistedPeople.size() ; i++) {
			sb.append("<tr>");
			sb.append("<td align=\"center\">"+vtRegistedPeople.get(i));
		}
		sb.append("<tr>");
		sb.append("	<td align=\"center\"> �`�p");
		sb.append("	<td align=\"center\"> "+strTotalCount);
		sb.append("</table>     ");
		
        sb.append("  </center></body></html>      ");


        String messageText = sb.toString();


		boolean sessionDebug = false;
		InternetAddress[] toAddress = new InternetAddress[mailList.size()];

		for(int i=0;i<mailList.size();i++){
			String[] address =(String[])mailList.get(i);
			toAddress[i]=new InternetAddress(address[1].toLowerCase(),address[0],"big5");
		}


		//InternetAddress[] addresses_cc = null;
		//InternetAddress[] addresses_bcc = null;



			// set properties...
			Properties props = System.getProperties();
			props.put("mail.host", mailserver);
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.auth", "true");

			// new Session service...
			//javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(props,null);
			javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(props, new SMTPAuthenticator(From, "crmbank2"));

			mailSession.setDebug(sessionDebug);
			
			
			// new Message...
			MimeMessage msg = new MimeMessage(mailSession);

			// set sender...
			msg.setFrom(new InternetAddress(From,"����H�� �D�����ȫO�M��","big5"));

			// set recipient...
			//address = InternetAddress.parse(to,false);


			msg.setRecipients(Message.RecipientType.TO, toAddress);
			//address_cc = InternetAddress.parse(cc,false);
			//msg.setRecipients(Message.RecipientType.CC , addresses_cc);
			//address_bcc = InternetAddress.parse(bcc,false);
			//msg.setRecipients(Message.RecipientType.BCC, addresses_bcc);

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
			FileWriter fw = new FileWriter("DBBKSendCounter.log", true);
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
