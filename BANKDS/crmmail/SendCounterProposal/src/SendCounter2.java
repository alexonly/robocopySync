import javax.mail.*;
import javax.mail.internet.*;
//import javax.activation.*;
import java.util.*;
import java.io.*;
//import java.net.*;
import java.sql.*;

class SendCounter2 {
	public static void main(String[] args) {
        boolean blnIsException = false;

        //---<---���------
        Calendar today = Calendar.getInstance();
        long lngYesterday = today.getTimeInMillis()-86400000;
        today.setTimeInMillis(lngYesterday);
        int intyear = today.get(Calendar.YEAR);
        int intmonth = today.get(Calendar.MONTH)+1;
        int intday = today.get(Calendar.DATE);
        int DayOfWeek = today.get(Calendar.DAY_OF_WEEK);//���o���ѬO�P���X
        String strDate = String.valueOf(intyear)+String.valueOf(intmonth)+String.valueOf(intday);
        String strDateOutput = String.valueOf(intyear) + "/" + String.valueOf(intmonth) + "/" + String.valueOf(intday);
        //System.out.println(strDate);
        //System.out.println(strDateOutput);
        //------���--->---

        /* �i���ɮױo���H���A�G����
        //---<---get counter data------
        String brad = "0";
        String brad1 = "0";
        try {
            URL objURL = new URL("http://192.168.100.34:9080/ProInput/" + strDate);
            BufferedReader br = new BufferedReader(new InputStreamReader(objURL.openStream()));
            String strHTML = "";
            do {
                strHTML = br.readLine();
                if (strHTML != null){
                    brad = strHTML;
                }
            } while (strHTML != null);
            br.close();
            br = null;
            objURL = null;


            URL objURL1 = new URL("http://192.168.100.34:9080/ProInput/" + strDate + "_home");
            BufferedReader br1 = new BufferedReader(new InputStreamReader(objURL1.openStream()));
            String strHTML1 = "";
            do {
                strHTML1 = br1.readLine();
                if (strHTML1 != null){
                    brad1 = strHTML1;
                }
            } while (strHTML1 != null);
            br1.close();
            br1 = null;
            objURL1 = null;	
        }
        catch (Exception e) {
            //---<---log------
            writeLog(strDateOutput + ": get url counter's exception: " + e.toString());
            //------log--->---
        }
        //------get counter data--->---
        */

        //---<---get mail address(to) & content------
        //mail to �H�����DB���^�����	Wen 20110414
        String strMailTo = "";	//�����mail
        String strContent = "";
/*
        try {
            FileReader fr = new FileReader("mailto.txt");
            BufferedReader br = new BufferedReader(fr);
            strMailTo = br.readLine();
            strContent = br.readLine();
            br.close();
            fr.close();
            br = null;
            fr = null;
        }
        catch (Exception e) {
            //---<---log------
            writeLog(strDateOutput + ": get mail address's exception: " + e.toString());
            //------log--->---
            blnIsException = true;
        }
*/
        //-------get mail address(to) & content------

        //---<---get db data------
        String strHomePageCountTimes = "";      // �����H���q����H�ط~�ȡr
        String strHomePageCountPeople = "";      // �����H�ơq����H�ط~�ȡr
        String strInsertCountTimesT = "";      // �s�W�ǲΫ��H���q����H�ط~�ȡr
        String strInsertCountPeopleT = "";      // �s�W�ǲΫ��H�ơq����H�ط~�ȡr
        String strInsertCountTimesI = "";      // �s�W��ꫬ�H���q����H�ط~�ȡr
        String strInsertCountPeopleI = "";      // �s�W��ꫬ�H�ơq����H�ط~�ȡr
        String strPrintCountTimes = "";      // �C�L�H���q����H�ط~�ȡr
        String strPrintCountPeople = "";      // �C�L�H�ơq����H�ط~�ȡr
		
		String strHomePageWeekCountTimes = "";      // �����H���q����H�ط~�ȡrper week
		double avgHomePageWeekCountTimes = 0.0;      // ���������H���q����H�ط~�ȡrper week
        String strHomePageWeekCountPeople = "";      // �����H�ơq����H�ط~�ȡrper week
		double avgHomePageWeekCountPeople = 0.0;      // ���������H�ơq����H�ط~�ȡrper week
        String strInsertWeekCountTimesT = "";      // �s�W�ǲΫ��H���q����H�ط~�ȡrper week
        double avgInsertWeekCountTimesT = 0.0;      // �����s�W�ǲΫ��H���q����H�ط~�ȡrper week
		String strInsertWeekCountPeopleT = "";      // �s�W�ǲΫ��H�ơq����H�ط~�ȡrper week
        double avgInsertWeekCountPeopleT = 0.0;      // �����s�W�ǲΫ��H�ơq����H�ط~�ȡrper week
		String strInsertWeekCountTimesI = "";      // �s�W��ꫬ�H���q����H�ط~�ȡrper week
        double avgInsertWeekCountTimesI = 0.0;      // �����s�W��ꫬ�H���q����H�ط~�ȡrper week
		String strInsertWeekCountPeopleI = "";      // �s�W��ꫬ�H�ơq����H�ط~�ȡrper week
        double avgInsertWeekCountPeopleI = 0.0;      // �����s�W��ꫬ�H�ơq����H�ط~�ȡrper week
		String strPrintWeekCountTimes = "";      // �C�L�H���q����H�ط~�ȡrper week
        double avgPrintWeekCountTimes = 0.0;      // �����C�L�H���q����H�ط~�ȡrper week
		String strPrintWeekCountPeople = "";      // �C�L�H�ơq����H�ط~�ȡrper week
		double avgPrintWeekCountPeople = 0.0;      // �����C�L�H�ơq����H�ط~�ȡrper week
		
		String strHomePageMonthCountTimes = "";      // �����H���q����H�ط~�ȡrper month
		double avgHomePageMonthCountTimes = 0.0;      // ���������H���q����H�ط~�ȡrper month
        String strHomePageMonthCountPeople = "";      // �����H�ơq����H�ط~�ȡrper month
		double avgHomePageMonthCountPeople = 0.0;      // ���������H�ơq����H�ط~�ȡrper month
        String strInsertMonthCountTimesT = "";      // �s�W�ǲΫ��H���q����H�ط~�ȡrper month
		double avgInsertMonthCountTimesT = 0.0;      // �����s�W�ǲΫ��H���q����H�ط~�ȡrper month
        String strInsertMonthCountPeopleT = "";      // �s�W�ǲΫ��H�ơq����H�ط~�ȡrper month
		double avgInsertMonthCountPeopleT = 0.0;      // �����s�W�ǲΫ��H�ơq����H�ط~�ȡrper month
        String strInsertMonthCountTimesI = "";      // �s�W��ꫬ�H���q����H�ط~�ȡrper month
		double avgInsertMonthCountTimesI = 0.0;      // �����s�W��ꫬ�H���q����H�ط~�ȡrper month
        String strInsertMonthCountPeopleI = "";      // �s�W��ꫬ�H�ơq����H�ط~�ȡrper month
		double avgInsertMonthCountPeopleI = 0.0;      // �����s�W��ꫬ�H�ơq����H�ط~�ȡrper month
        String strPrintMonthCountTimes = "";      // �C�L�H���q����H�ط~�ȡrper month
		double avgPrintMonthCountTimes = 0.0;      // �����C�L�H���q����H�ط~�ȡrper month
        String strPrintMonthCountPeople = "";      // �C�L�H�ơq����H�ط~�ȡrper month
		double avgPrintMonthCountPeople = 0.0;      // �����C�L�H�ơq����H�ط~�ȡrper month

        String strHomePageCountTimesBank = "";      // �����H���q����@�زz�M�r
		double avgHomePageCountTimesBank = 0.0;      // ���������H���q����@�زz�M�r
        String strHomePageCountPeopleBank = "";      // �����H�ơq����@�زz�M�r
		double avgHomePageCountPeopleBank = 0.0;      // ���������H�ơq����@�زz�M�r
        String strInsertCountTimesTBank = "";      // �s�W�ǲΫ��H���q����@�زz�M�r
		double avgInsertCountTimesTBank = 0.0;      // �����s�W�ǲΫ��H���q����@�زz�M�r
        String strInsertCountPeopleTBank = "";      // �s�W�ǲΫ��H�ơq����@�زz�M�r
		double avgInsertCountPeopleTBank = 0.0;      // �����s�W�ǲΫ��H�ơq����@�زz�M�r
        String strInsertCountTimesIBank = "";      // �s�W��ꫬ�H���q����@�زz�M�r
		double avgInsertCountTimesIBank = 0.0;      // �����s�W��ꫬ�H���q����@�زz�M�r
        String strInsertCountPeopleIBank = "";      // �s�W��ꫬ�H�ơq����@�زz�M�r
		double avgInsertCountPeopleIBank = 0.0;      // �����s�W��ꫬ�H�ơq����@�زz�M�r
        String strPrintCountTimesBank = "";      // �C�L�H���q����@�زz�M�r
		double avgPrintCountTimesBank = 0.0;      // �����C�L�H���q����@�زz�M�r
        String strPrintCountPeopleBank = "";      // �C�L�H�ơq����@�زz�M�r
		double avgPrintCountPeopleBank = 0.0;      // �����C�L�H�ơq����@�زz�M�r

        String strHomePageCountName = "";      // �����m�W
        String strInsertCountNameT = "";      // �s�W�ǲΫ��m�W
        String strInsertCountNameI = "";      // �s�W��ꫬ�m�W
        String strPrintCountName = "";      // �C�L�m�W
		
		//String strEmployeeAllCount = "";		//���q�M��+�i�~���u��
		//String strEmployee_1_Count = "";		//���q�M�ۭ��u��(�S�Ψ�)
		//String strEmployee_2_Count = "";		//���q�i�~���u��(�S�Ψ�)
		
		int EmployeeCount = 1;			//���q�M��+�i�~���u��
		int dayCount = 1;				//�C�g�ΨC���ڭp��Ѽ�(�����g����)
		
		boolean debug = false;

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        }
        catch (Exception e1) {
            System.out.println(e1.toString());
            blnIsException = true;
        }

        Connection objConn = null;
        Connection objConnBankUser = null;
        try {
            //---<---����------
            objConn = DriverManager.getConnection("jdbc:odbc:CFHCDW", "suguser", "suguser");
            //------����--->---

            //---<---ws900xxxx------
            //objConn = DriverManager.getConnection("jdbc:odbc:P1", "suguser", "suguser");
            //------ws900xxxx--->---
			
            //---<---get mail address(to) & content------
            //mail to �H�����DB���^�����	Wen 20110414
            PreparedStatement mailPstmt = objConn.prepareStatement("SELECT * FROM INSDB.DAILYSTATS_RECEIVER WHERE DAILYX = '�������ӫ~��ĳ��' WITH UR");
            ResultSet mailRs = mailPstmt.executeQuery();
            int mailCounter = 0;
            while(mailRs.next()){
            	if(mailCounter == 0){
            		strMailTo = mailRs.getString("RC_MAIL"); 
            	}else{
            		strMailTo = strMailTo + "," + mailRs.getString("RC_MAIL"); 
            	}
            	mailCounter++;
            }
            if(debug)	writeLog("strMailTo: "+strMailTo);
            //-------get mail address(to) & content------
            
			//---<---�d�߭��u�H��------
            // �d�ߤ��q�M��+�i�~�H��
			if(debug)	writeLog("�d�ߤ��q�M��+�i�~�H�� START");
            //objPStmt = objConn.prepareStatement("select sum(case substr(cedvno,3,1) when '1' then 1 when '4' then 1 else 0 end) as all, sum(case substr(cedvno,3,1) when '1' then 1 else 0 end) as a, sum(case substr(cedvno,3,1) when '4' then 1 else 0 end) as b from insdb.web_basic with ur");
			PreparedStatement objPStmt = objConn.prepareStatement("select sum(case substr(cedvno,3,1) when '1' then 1 when '4' then 1 else 0 end)  from insdb.web_basic with ur");
            ResultSet objRS = objPStmt.executeQuery();
            while (objRS.next()) {
//				EmployeeCount = Integer.parseInt(objRS.getString(1));	//Wen�G�����D��쬰����n�g�o�o�����
            	EmployeeCount = objRS.getInt(1);
                //strEmployee_1_Count = objRS.getString(2);
				//strEmployee_2_Count = objRS.getString(3);
            }
			if(debug)	writeLog("�d�ߤ��q�M��+�i�~�H�� END: "+EmployeeCount);
			//------�d�߭��u�H��--->---

            //---<---����------
            // �����q����H�ط~�ȡr�C��
			if(debug)	writeLog("�����q����H�ط~�ȡr�C�� START");
            objPStmt = objConn.prepareStatement("select sum(cnt), count(distinct agidno) from insdb.sug_count1 a inner join insdb.web_basic b on a.agidno = b.ceempid where substr(b.cedvno,3,1) in ('1','4')  and agidno != '' and in_day=(current date - 1 days) and isinv in ('I', 'T', 'Q', 'CI', 'CT', 'CQ', 'CB') with ur");
            objRS = objPStmt.executeQuery();
            while (objRS.next()) {
                //strHomePageCountTimes = objRS.getString(1) == null ? "0" : objRS.getString(1);	//Wen 20110221
            	strHomePageCountTimes = String.valueOf(objRS.getInt(1));	//�ϥ�getString()�|��SQLException: No data found���D�A�G�令getInt()Wen 20110221
                strHomePageCountPeople = objRS.getString(2);
            }
			if(debug)	writeLog("�����q����H�ط~�ȡr�C�� END");
            
            //�����q����H�ط~�ȡr�C�g
			if(debug)	writeLog("�����q����H�ط~�ȡr�C�g START");
            objPStmt = objConn.prepareStatement("select sum(a.cnt), count(distinct a.agidno), count(distinct a.in_day) from insdb.sug_count1 a inner join insdb.web_basic b on a.agidno = b.ceempid where substr(b.cedvno,3,1) in ('1','4') and a.agidno != '' and DAYOFWEEK_ISO(a.in_day) <6  and a.in_day >= (current date -  7 days) and a.in_day < current date and isinv in ('I', 'T', 'Q', 'CI', 'CT', 'CQ', 'CB') with ur");
            objRS = objPStmt.executeQuery();
            while (objRS.next()) {
                //strHomePageWeekCountTimes = objRS.getString(1) == null ? "0" : objRS.getString(1);	//Wen 20110221
            	strHomePageWeekCountTimes = String.valueOf(objRS.getInt(1));	//Wen 20110225
                strHomePageWeekCountPeople = objRS.getString(2);
				dayCount = Integer.parseInt( objRS.getString(3) );
            }
			if(debug)	writeLog("�����q����H�ط~�ȡr�C�g END");
			avgHomePageWeekCountTimes = round(Double.parseDouble(strHomePageWeekCountTimes)/dayCount);
			avgHomePageWeekCountPeople = round(Double.parseDouble(strHomePageWeekCountPeople)/EmployeeCount*100);
    		
            //�����q����H�ط~�ȡr�C��
			if(debug)	writeLog("�����q����H�ط~�ȡr�C�� START");
            objPStmt = objConn.prepareStatement("select sum(a.cnt), count(distinct a.agidno), count(distinct a.in_day) from insdb.sug_count1 a inner join insdb.web_basic b on a.agidno = b.ceempid where substr(b.cedvno,3,1) in ('1','4') and a.agidno != '' and DAYOFWEEK_ISO(a.in_day) <6  and a.in_day >= (current date - 30 days) and a.in_day < current date and isinv in ('I', 'T', 'Q', 'CI', 'CT', 'CQ', 'CB') with ur");
            objRS = objPStmt.executeQuery();
            while (objRS.next()) {
                //strHomePageMonthCountTimes = objRS.getString(1);
            	strHomePageMonthCountTimes = String.valueOf(objRS.getInt(1));	//Wen 20110225
                strHomePageMonthCountPeople = objRS.getString(2);
				dayCount = Integer.parseInt( objRS.getString(3) );
            }
			if(debug)	writeLog("�����q����H�ط~�ȡr�C�� END");
			avgHomePageMonthCountTimes = round(Double.parseDouble(strHomePageMonthCountTimes)/dayCount);
			avgHomePageMonthCountPeople = round(Double.parseDouble(strHomePageMonthCountPeople)/EmployeeCount*100);
            
            /*
            // �����q����@�زz�M�r
            objPStmt = objConn.prepareStatement("select sum(cnt), count(distinct agidno) from insdb.sug_count1 where agidno != '' and in_day=(current date - 1 days) and (isinv='BI' or isinv='BT' or isinv='BQ')");
            objRS = objPStmt.executeQuery();
            while (objRS.next()) {
                strHomePageCountTimesBank = objRS.getString(1);
                strHomePageCountPeopleBank = objRS.getString(2);
            }

            // �����q����@�زz�M�r�G�m�W
            objPStmt = objConn.prepareStatement("select sum(a.cnt) as SUM, b.name from insdb.sug_count1 a, insdb.bnk_invest_ser b where a.agidno=b.idno and a.agidno != '' and a.in_day=(current date - 1 days) and (a.isinv='BI' or a.isinv='BT' or a.isinv='BQ') group by b.name");
            objRS = objPStmt.executeQuery();
            while (objRS.next()) {
                strHomePageCountName += " " + objRS.getString("NAME") + objRS.getString("SUM") + "�� ";
            }
            */
            //------����--->---

            //---<---�s�W�ǲ�(CT)�B�Q��(CB)��------
            // �s�W�ǲ�(CT)�B�Q��(CB)���q����H�ط~�ȡr
			if(debug)	writeLog("�s�W�ǲ�(CT)�B�Q��(CB)���q����H�ط~�ȡrSTART");
            objPStmt = objConn.prepareStatement("select sum(cnt), count(*) from insdb.sug_count a inner join insdb.web_basic b on a.agidno = b.ceempid where substr(b.cedvno,3,1) in ('1','4') and agidno!='' and in_day=(current date - 1 days) and isinv in ('CT', 'CB') with ur");
            objRS = objPStmt.executeQuery();
            while (objRS.next()) {
                //strInsertCountTimesT = objRS.getString(1);
            	strInsertCountTimesT = String.valueOf(objRS.getInt(1));	//Wen 20110225
                strInsertCountPeopleT = objRS.getString(2);
            }
			if(debug)	writeLog("�s�W�ǲ�(CT)�B�Q��(CB)���q����H�ط~�ȡrEND");
            
            // �s�W�ǲ�(CT)�B�Q��(CB)���q����H�ط~�ȡr�C�g
			if(debug)	writeLog("�s�W�ǲ�(CT)�B�Q��(CB)���q����H�ط~�ȡr�C�g START");
            objPStmt = objConn.prepareStatement("select sum(a.cnt), count(distinct a.agidno), count(distinct a.in_day) from insdb.sug_count a inner join insdb.web_basic b on a.agidno = b.ceempid where substr(b.cedvno,3,1) in ('1','4') and a.agidno!='' and DAYOFWEEK_ISO(a.in_day) <6 and a.in_day >= (current date -  7 days) and a.in_day < current date and isinv in ('T', 'CT', 'CB') with ur");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				//strInsertWeekCountTimesT = objRS.getString(1);
				strInsertWeekCountTimesT = String.valueOf(objRS.getInt(1));	//Wen 20110225
				strInsertWeekCountPeopleT = objRS.getString(2);
				dayCount = Integer.parseInt( objRS.getString(3) );
			}
			if(debug)	writeLog("�s�W�ǲ�(CT)�B�Q��(CB)���q����H�ط~�ȡr�C�g END");
			avgInsertWeekCountTimesT = round(Double.parseDouble(strInsertWeekCountTimesT)/dayCount);
			avgInsertWeekCountPeopleT = round(Double.parseDouble(strInsertWeekCountPeopleT)/EmployeeCount*100);
            
            // �s�W�ǲ�(CT)�B�Q��(CB)���q����H�ط~�ȡr�C��
			if(debug)	writeLog("�s�W�ǲ�(CT)�B�Q��(CB)���q����H�ط~�ȡr�C�� START");
            objPStmt = objConn.prepareStatement("select sum(a.cnt), count(distinct a.agidno), count(distinct a.in_day) from insdb.sug_count a inner join insdb.web_basic b on a.agidno = b.ceempid where substr(b.cedvno,3,1) in ('1','4') and a.agidno!='' and DAYOFWEEK_ISO(a.in_day) <6  and a.in_day >= (current date - 30 days) and a.in_day < current date and isinv in ('T', 'CT', 'CB') with ur");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				//strInsertMonthCountTimesT = objRS.getString(1);
				strInsertMonthCountTimesT = String.valueOf(objRS.getInt(1));	//Wen 20110225
				strInsertMonthCountPeopleT = objRS.getString(2);
				dayCount = Integer.parseInt( objRS.getString(3) );
			}
			if(debug)	writeLog("�s�W�ǲ�(CT)�B�Q��(CB)���q����H�ط~�ȡr�C�� END");
			avgInsertMonthCountTimesT = round(Double.parseDouble(strInsertMonthCountTimesT)/dayCount);
			avgInsertMonthCountPeopleT = round(Double.parseDouble(strInsertMonthCountPeopleT)/EmployeeCount*100);

            /*
            // �s�W�ǲΫ��q����@�زz�M�r
            objPStmt = objConn.prepareStatement("select sum(cnt), count(*) from insdb.sug_count where agidno!='' and in_day=(current date - 1 days) and isinv='BT'");
            objRS = objPStmt.executeQuery();
            while (objRS.next()) {
                strInsertCountTimesTBank = objRS.getString(1);
                strInsertCountPeopleTBank = objRS.getString(2);
            }

            // �s�W�ǲΫ��q����@�زz�M�r�G�m�W
            objPStmt = objConn.prepareStatement("select sum(a.cnt) as SUM, b.name from insdb.sug_count a, insdb.bnk_invest_ser b where a.agidno=b.idno and a.agidno!='' and a.in_day=(current date - 1 days) and a.isinv='BT' group by b.name");
            objRS = objPStmt.executeQuery();
            while (objRS.next()) {
                strInsertCountNameT += " " + objRS.getString("NAME") + objRS.getString("SUM") + "�� ";
            }
            */
            //------�s�W�ǲΫ�--->---

            //---<---�s�W��ꫬ------
            // �s�W��ꫬ�q����H�ط~�ȡr
			if(debug)	writeLog("�s�W��ꫬ�q����H�ط~�ȡr START");
            objPStmt = objConn.prepareStatement("select sum(cnt), count(*) from insdb.sug_count a inner join insdb.web_basic b on a.agidno = b.ceempid where substr(b.cedvno,3,1) in ('1','4') and agidno!='' and in_day=(current date - 1 days) and isinv='CI' with ur");
            objRS = objPStmt.executeQuery();
            while (objRS.next()) {
                //strInsertCountTimesI = objRS.getString(1);
                strInsertCountTimesI = String.valueOf(objRS.getInt(1));	//Wen 20110225
                strInsertCountPeopleI = objRS.getString(2);
            }
			if(debug)	writeLog("�s�W��ꫬ�q����H�ط~�ȡr END");
            
            // �s�W��ꫬ�q����H�ط~�ȡr�C�g
			if(debug)	writeLog("�s�W��ꫬ�q����H�ط~�ȡr�C�g  START");
            objPStmt = objConn.prepareStatement("select sum(a.cnt), count(distinct a.agidno), count(distinct a.in_day) from insdb.sug_count a inner join insdb.web_basic b on a.agidno = b.ceempid where substr(b.cedvno,3,1) in ('1','4') and a.agidno!='' and DAYOFWEEK_ISO(a.in_day) <6  and a.in_day >= (current date -  7 days) and a.in_day < current date and isinv in ('I', 'CI') with ur");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				//strInsertWeekCountTimesI = objRS.getString(1);
				strInsertWeekCountTimesI = String.valueOf(objRS.getInt(1));	//Wen 20110225
				strInsertWeekCountPeopleI = objRS.getString(2);
				dayCount = Integer.parseInt( objRS.getString(3) );
			}
			if(debug)	writeLog("�s�W��ꫬ�q����H�ط~�ȡr�C�g  END");
			avgInsertWeekCountTimesI = round(Double.parseDouble(strInsertWeekCountTimesI)/dayCount);
			avgInsertWeekCountPeopleI = round(Double.parseDouble(strInsertWeekCountPeopleI)/EmployeeCount*100);
            
            // �s�W��ꫬ�q����H�ط~�ȡr�C��
			if(debug)	writeLog("�s�W��ꫬ�q����H�ط~�ȡr�C��  START");
            objPStmt = objConn.prepareStatement("select sum(a.cnt), count(distinct a.agidno), count(distinct a.in_day) from insdb.sug_count a inner join insdb.web_basic b on a.agidno = b.ceempid where substr(b.cedvno,3,1) in ('1','4') and a.agidno!='' and DAYOFWEEK_ISO(a.in_day) <6  and a.in_day >= (current date - 30 days) and a.in_day < current date and isinv in ('I', 'CI') with ur");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				//strInsertMonthCountTimesI = objRS.getString(1);
				strInsertMonthCountTimesI = String.valueOf(objRS.getInt(1));	//Wen 20110225
				strInsertMonthCountPeopleI = objRS.getString(2);
				dayCount = Integer.parseInt( objRS.getString(3) );
			}
			if(debug)	writeLog("�s�W��ꫬ�q����H�ط~�ȡr�C�� END");
			avgInsertMonthCountTimesI = round(Double.parseDouble(strInsertMonthCountTimesI)/dayCount);
			avgInsertMonthCountPeopleI = round(Double.parseDouble(strInsertMonthCountPeopleI)/EmployeeCount*100);

            /*
            // �s�W��ꫬ�q����@�زz�M�r
            objPStmt = objConn.prepareStatement("select sum(cnt), count(*) from insdb.sug_count where agidno!='' and in_day=(current date - 1 days) and isinv='BI'");
            objRS = objPStmt.executeQuery();
            while (objRS.next()) {
                strInsertCountTimesIBank = objRS.getString(1);
                strInsertCountPeopleIBank = objRS.getString(2);
            }

            // �s�W��ꫬ�q����@�زz�M�r�G�m�W
            objPStmt = objConn.prepareStatement("select sum(a.cnt) as SUM, b.name from insdb.sug_count a, insdb.bnk_invest_ser b where a.agidno=b.idno and a.agidno!='' and a.in_day=(current date - 1 days) and a.isinv='BI' group by b.name");
            objRS = objPStmt.executeQuery();
            while (objRS.next()) {
                strInsertCountNameI += " " + objRS.getString("NAME") + objRS.getString("SUM") + "�� ";
            }
            */
            //------�s�W��ꫬ--->---

            //---<---�C�L------
            // �C�L�q����H�ط~�ȡr
			//CT�G��ضǲΡBCB�G��اQ�ܡBCI�G��ا��BCC�G��ئ^�ǫO��
			if(debug)	writeLog("�C�L�q����H�ط~�ȡr  START");
            Calendar objCal1 = Calendar.getInstance();
            Calendar objCal2 = Calendar.getInstance();
            objCal1.add(Calendar.DATE, -1);
            String strPrintBeginTime = objCal1.get(Calendar.YEAR) + "-" + (objCal1.get(Calendar.MONTH) + 1) + "-" + objCal1.get(Calendar.DATE) + " 00:00:00";
            String strPrintEndTime = objCal2.get(Calendar.YEAR) + "-" + (objCal2.get(Calendar.MONTH) + 1) + "-" + objCal2.get(Calendar.DATE) + " 00:00:00";
            //objPStmt = objConn.prepareStatement("select count(*), count(distinct agidno) from insdb.sug_pcount where agidno != '' and pdate between '" + strPrintBeginTime + "' and '" + strPrintEndTime + "' and (isinv='T' or isinv='I' or isinv='C') with ur");
			objPStmt = objConn.prepareStatement("select count(*), count(distinct agidno) from insdb.sug_pcount  a inner join insdb.web_basic b on a.agidno = b.ceempid where substr(b.cedvno,3,1) in ('1','4') and  agidno != '' and date(pdate) = (current date - 1 days) and isinv in ('T', 'I', 'C', 'CT', 'CB', 'CI', 'CC') with ur");
            objRS = objPStmt.executeQuery();
            while (objRS.next()) {
                strPrintCountTimes = objRS.getString(1);
                strPrintCountPeople = objRS.getString(2);
            }
            if(debug)	writeLog("�C�L�q����H�ط~�ȡr  END");
			
            // �C�L�q����H�ط~�ȡr�C�g
			if(debug)	writeLog("�C�L�q����H�ط~�ȡr�C�g  START");
            objPStmt = objConn.prepareStatement("select count(*), count(distinct a.agidno), count(distinct date(a.pdate)) from insdb.sug_pcount a inner join insdb.web_basic b on a.agidno = b.ceempid where substr(b.cedvno,3,1) in ('1','4') and a.agidno != '' and DAYOFWEEK_ISO(a.pdate) <6  and date(a.pdate) >= (current date -  7 day) and date(a.pdate) < current date and isinv in ('T', 'I', 'C', 'CT', 'CB', 'CI', 'CC') with ur");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				strPrintWeekCountTimes = objRS.getString(1);
				strPrintWeekCountPeople = objRS.getString(2);
				dayCount = Integer.parseInt( objRS.getString(3) );
			}
			if(debug)	writeLog("�C�L�q����H�ط~�ȡr�C�g  END");
			avgPrintWeekCountTimes = round(Double.parseDouble(strPrintWeekCountTimes)/dayCount);
			avgPrintWeekCountPeople = round(Double.parseDouble(strPrintWeekCountPeople)/EmployeeCount*100);
            
            // �C�L�q����H�ط~�ȡr�C��
			if(debug)	writeLog("�C�L�q����H�ط~�ȡr�C��  START");
			objPStmt = objConn.prepareStatement("select count(*), count(distinct a.agidno), count(distinct date(a.pdate)) from insdb.sug_pcount a inner join insdb.web_basic b on a.agidno = b.ceempid where substr(b.cedvno,3,1) in ('1','4') and a.agidno != '' and DAYOFWEEK_ISO(a.pdate) <6  and date(a.pdate) >= (current date - 30 day) and date(a.pdate) < current date and isinv in ('T', 'I', 'C', 'CT', 'CB', 'CI', 'CC') with ur");
			objRS = objPStmt.executeQuery();
			while (objRS.next()) {
				strPrintMonthCountTimes = objRS.getString(1);
				strPrintMonthCountPeople = objRS.getString(2);
				dayCount = Integer.parseInt( objRS.getString(3) );
			}
			if(debug)	writeLog("�C�L�q����H�ط~�ȡr�C��  END");
			avgPrintMonthCountTimes = round(Double.parseDouble(strPrintMonthCountTimes)/dayCount);
			avgPrintMonthCountPeople = round(Double.parseDouble(strPrintMonthCountPeople)/EmployeeCount*100);

            /*
            // �C�L�q����@�زz�M�r
            strPrintBeginTime = objCal1.get(Calendar.YEAR) + "-" + (objCal1.get(Calendar.MONTH) + 1) + "-" + objCal1.get(Calendar.DATE) + " 00:00:00";
            strPrintEndTime = objCal2.get(Calendar.YEAR) + "-" + (objCal2.get(Calendar.MONTH) + 1) + "-" + objCal2.get(Calendar.DATE) + " 00:00:00";
            objPStmt = objConn.prepareStatement("select count(*), count(distinct agidno) from insdb.sug_pcount where agidno != '' and pdate between '" + strPrintBeginTime + "' and '" + strPrintEndTime + "' and (isinv='BT' or isinv='BI' or isinv='BC')");
            objRS = objPStmt.executeQuery();
            while (objRS.next()) {
                strPrintCountTimesBank = objRS.getString(1);
                strPrintCountPeopleBank = objRS.getString(2);
            }

            // �C�L�q����@�زz�M�r�G�m�W
            strPrintBeginTime = objCal1.get(Calendar.YEAR) + "-" + (objCal1.get(Calendar.MONTH) + 1) + "-" + objCal1.get(Calendar.DATE) + " 00:00:00";
            strPrintEndTime = objCal2.get(Calendar.YEAR) + "-" + (objCal2.get(Calendar.MONTH) + 1) + "-" + objCal2.get(Calendar.DATE) + " 00:00:00";
            objPStmt = objConn.prepareStatement("select count(agidno) as SUM, b.name from insdb.sug_pcount a, insdb.bnk_invest_ser b where a.agidno=b.idno and a.agidno != '' and a.pdate between '" + strPrintBeginTime + "' and '" + strPrintEndTime + "' and (a.isinv='BT' or a.isinv='BI' or a.isinv='BC') group by b.name");
            objRS = objPStmt.executeQuery();
            while (objRS.next()) {
                strPrintCountName += " " + objRS.getString("NAME") + objRS.getString("SUM") + "�� ";
            }
            */
            //------�C�L--->---
			
			//���g�z�Ϊ���έp�Ʀr �H��H�X�e�N�C�骺�ϥΤH�ƤH����Ƽg�JINSDB.DAILYX_CRMDAY	Wen 20110413
			objPStmt = objConn.prepareStatement("INSERT INTO INSDB.DAILYX_CRMDAY (COUNT_DATE, MAINITEM, ITEMNAME, CEDVNO, NUMBER_PEOPLE, PEOPLE) VALUES (current date, '�������ӫ~��ĳ��1', '��ĳ�Ѩϥζq', '0', ?, ?)");
			objPStmt.setInt(1, Integer.parseInt(strHomePageCountPeople));
			objPStmt.setInt(2, Integer.parseInt(strHomePageCountTimes));
			objPStmt.execute();
			
			objPStmt = objConn.prepareStatement("INSERT INTO INSDB.DAILYX_CRMDAY (COUNT_DATE, MAINITEM, ITEMNAME, CEDVNO, NUMBER_PEOPLE, PEOPLE) VALUES (current date, '�������ӫ~��ĳ��2', '�s��ǲΫ�', '0', ?, ?)");
			objPStmt.setInt(1, Integer.parseInt(strInsertCountPeopleT));
			objPStmt.setInt(2, Integer.parseInt(strInsertCountTimesT));
			objPStmt.execute();
			
			objPStmt = objConn.prepareStatement("INSERT INTO INSDB.DAILYX_CRMDAY (COUNT_DATE, MAINITEM, ITEMNAME, CEDVNO, NUMBER_PEOPLE, PEOPLE) VALUES (current date, '�������ӫ~��ĳ��3', '�s���ꫬ', '0', ?, ?)");
			objPStmt.setInt(1, Integer.parseInt(strInsertCountPeopleI));
			objPStmt.setInt(2, Integer.parseInt(strInsertCountTimesI));
			objPStmt.execute();
			
			objPStmt = objConn.prepareStatement("INSERT INTO INSDB.DAILYX_CRMDAY (COUNT_DATE, MAINITEM, ITEMNAME, CEDVNO, NUMBER_PEOPLE, PEOPLE) VALUES (current date, '�������ӫ~��ĳ��4', '�C�L��ĳ��', '0', ?, ?)");
			objPStmt.setInt(1, Integer.parseInt(strPrintCountPeople));
			objPStmt.setInt(2, Integer.parseInt(strPrintCountTimes));
			objPStmt.execute();
			//
			
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

//      String mailserver   = "cathaylife.com.tw";
        String mailserver = "sendmail.cathaylife.com.tw";	//Wen modify 2009/12/11
        String From         = "crm@cathaylife.com.tw";
        String to = strMailTo;
        //String to           = "brad1029@cathaylife.com.tw,yanshang@cathaylife.com.tw,conee@cathaylife.com.tw,cctsai@cathaylife.com.tw,aileen@cathaylife.com.tw";
        //String to           = "brad1029@cathaylife.com.tw";
        //String cc = "brad1029@cathaylife.com.tw";
        //String bcc = "brad1029@cathaylife.com.tw";
        String Subject      = strDateOutput+" �������ӫ~��ĳ�ѨϥΪ��p";
        String messageText  = "<< " + strDateOutput + " �������ӫ~��ĳ�ѨϥΪ��p >>\n";
        messageText += "--����H�ط~��--��ĳ�Ѩϥβv�G\n";
				messageText += "����G" + strHomePageCountTimes + " �H���F " + strHomePageCountPeople + "�H�ơC\n";
				messageText += "���g�`�p�G" + strHomePageWeekCountTimes + " �H���F " + strHomePageWeekCountPeople + "�H�ơC\n";
				messageText += "���g�����G" + avgHomePageWeekCountTimes + " �H���F " + avgHomePageWeekCountPeople + "% �ϥΤH�Ƥ�ҡC\n";
    		messageText += "�����`�p�G" + strHomePageMonthCountTimes + " �H���F " + strHomePageMonthCountPeople + "�H�ơC\n";
				messageText += "���륭���G" + avgHomePageMonthCountTimes + " �H���F " + avgHomePageMonthCountPeople + "% �ϥΤH�Ƥ�ҡC\n\n";
    
				messageText += "--����H�ط~��--�s��ǲΫ��G\n";
				messageText += "����G" + strInsertCountTimesT + " �H���F " + strInsertCountPeopleT + "�H�ơC\n";
    		messageText += "���g�`�p�G" + strInsertWeekCountTimesT + " �H���F " + strInsertWeekCountPeopleT + "�H�ơC\n";
				messageText += "���g�����G" + avgInsertWeekCountTimesT + " �H���F " + avgInsertWeekCountPeopleT + "% �ϥΤH�Ƥ�ҡC\n";
				messageText += "�����`�p�G" + strInsertMonthCountTimesT + " �H���F " + strInsertMonthCountPeopleT + "�H�ơC\n";
				messageText += "���륭���G" + avgInsertMonthCountTimesT + " �H���F " + avgInsertMonthCountPeopleT + "% �ϥΤH�Ƥ�ҡC\n\n";
		    
				messageText += "--����H�ط~��--�s���ꫬ�G\n";
				messageText += "����G" + strInsertCountTimesI + " �H���F " + strInsertCountPeopleI + "�H�ơC\n";
				messageText += "���g�`�p�G" + strInsertWeekCountTimesI + " �H���F " + strInsertWeekCountPeopleI + "�H�ơC\n";
				messageText += "���g�����G" + avgInsertWeekCountTimesI + " �H���F " + avgInsertWeekCountPeopleI + "% �ϥΤH�Ƥ�ҡC\n";
				messageText += "�����`�p�G" + strInsertMonthCountTimesI + " �H���F " + strInsertMonthCountPeopleI + "�H�ơC\n";
				messageText += "���륭���G" + avgInsertMonthCountTimesI + " �H���F " + avgInsertMonthCountPeopleI + "% �ϥΤH�Ƥ�ҡC\n\n";
	        
				messageText += "--����H�ط~��--�C�L��ĳ�ѡG\n";
				messageText += "����G" + strPrintCountTimes + " �H���F " + strPrintCountPeople + "�H�ơC\n";
				messageText += "���g�`�p�G" + strPrintWeekCountTimes + " �H���F " + strPrintWeekCountPeople + "�H�ơC\n";
				messageText += "���g�����G" + avgPrintWeekCountTimes + " �H���F " + avgPrintWeekCountPeople + "% �ϥΤH�Ƥ�ҡC\n";
				messageText += "�����`�p�G" + strPrintMonthCountTimes + " �H���F " + strPrintMonthCountPeople + "�H�ơC\n";
				messageText += "���륭���G" + avgPrintMonthCountTimes + " �H���F " + avgPrintMonthCountPeople + "% �ϥΤH�Ƥ�ҡC\n\n";
				messageText += "���G�ϥΤH�Ƥ�ҡJ��g�η��ϥΤH���`�p / �M�ۥ[�i�~�H��\n";
				messageText += "�@�@�M�ۥ[�i�~�H�ơJ"+EmployeeCount+"\n";
				/*
        messageText += "--����H�ط~��--\n";
        messageText += "��ĳ�Ѩϥβv�G" + ((strHomePageCountTimes != null) ? strHomePageCountTimes : "0") + " �H���F " + strHomePageCountPeople + "�H�ơC\n";
        messageText += "�s��ǲΫ��G" + ((strInsertCountTimesT != null) ? strInsertCountTimesT : "0") + " �H���F " + strInsertCountPeopleT + "�H�ơC\n";
        messageText += "�s���ꫬ�G" + ((strInsertCountTimesI != null) ? strInsertCountTimesI : "0") + " �H���F " + strInsertCountPeopleI + "�H�ơC\n";
        messageText += "�C�L��ĳ�ѡG" + strPrintCountTimes + " �H���F " + strPrintCountPeople + "�H�ơC\n\n";
        
        messageText += "--����H�ط~�� ���g�`�p--\n";
        messageText += "��ĳ�Ѩϥβv�G" + ((strHomePageWeekCountTimes != null) ? strHomePageWeekCountTimes : "0") + " �H���F " + strHomePageWeekCountPeople + "�H�ơC\n";
        messageText += "�s��ǲΫ��G" + ((strInsertWeekCountTimesT != null) ? strInsertWeekCountTimesT : "0") + " �H���F " + strInsertWeekCountPeopleT + "�H�ơC\n";
        messageText += "�s���ꫬ�G" + ((strInsertWeekCountTimesI != null) ? strInsertWeekCountTimesI : "0") + " �H���F " + strInsertWeekCountPeopleI + "�H�ơC\n";
        messageText += "�C�L��ĳ�ѡG" + strPrintWeekCountTimes + " �H���F " + strPrintWeekCountPeople + "�H�ơC\n\n";
        
        messageText += "--����H�ط~�� ���g����--\n";
        messageText += "��ĳ�Ѩϥβv�G" + ((strHomePageWeekCountTimes != null) ? String.valueOf(round(Double.parseDouble(strHomePageWeekCountTimes)/5)) : "0") + " �H���F " + String.valueOf(round(Double.parseDouble(strHomePageWeekCountPeople)/5)) + "�H�ơC\n";
        messageText += "�s��ǲΫ��G" + ((strInsertWeekCountTimesT != null) ? String.valueOf(round(Double.parseDouble(strInsertWeekCountTimesT)/5)) : "0") + " �H���F " + String.valueOf(round(Double.parseDouble(strInsertWeekCountPeopleT)/5)) + "�H�ơC\n";
        messageText += "�s���ꫬ�G" + ((strInsertWeekCountTimesI != null) ? String.valueOf(round(Double.parseDouble(strInsertWeekCountTimesI)/5)) : "0") + " �H���F " + String.valueOf(round(Double.parseDouble(strInsertWeekCountPeopleI)/5)) + "�H�ơC\n";
        messageText += "�C�L��ĳ�ѡG" + String.valueOf(round(Double.parseDouble(strPrintWeekCountTimes)/5)) + " �H���F " + String.valueOf(round(Double.parseDouble(strPrintWeekCountPeople)/5)) + "�H�ơC\n\n";
        
        messageText += "--����H�ط~�� �����`�p--\n";
        messageText += "��ĳ�Ѩϥβv�G" + ((strHomePageMonthCountTimes != null) ? strHomePageMonthCountTimes : "0") + " �H���F " + strHomePageMonthCountPeople + "�H�ơC\n";
        messageText += "�s��ǲΫ��G" + ((strInsertMonthCountTimesT != null) ? strInsertMonthCountTimesT : "0") + " �H���F " + strInsertMonthCountPeopleT + "�H�ơC\n";
        messageText += "�s���ꫬ�G" + ((strInsertMonthCountTimesI != null) ? strInsertMonthCountTimesI : "0") + " �H���F " + strInsertMonthCountPeopleI + "�H�ơC\n";
        messageText += "�C�L��ĳ�ѡG" + strPrintMonthCountTimes + " �H���F " + strPrintMonthCountPeople + "�H�ơC\n\n";
        
        messageText += "--����H�ط~�� ���륭��--\n";
        messageText += "��ĳ�Ѩϥβv�G" + ((strHomePageMonthCountTimes != null) ? String.valueOf(round(Double.parseDouble(strHomePageMonthCountTimes)/22)) : "0") + " �H���F " + String.valueOf(round(Double.parseDouble(strHomePageMonthCountPeople)/22)) + "�H�ơC\n";
        messageText += "�s��ǲΫ��G" + ((strInsertMonthCountTimesT != null) ? String.valueOf(round(Double.parseDouble(strInsertMonthCountTimesT)/22)) : "0") + " �H���F " + String.valueOf(round(Double.parseDouble(strInsertMonthCountPeopleT)/22)) + "�H�ơC\n";
        messageText += "�s���ꫬ�G" + ((strInsertMonthCountTimesI != null) ? String.valueOf(round(Double.parseDouble(strInsertMonthCountTimesI)/22)) : "0") + " �H���F " + String.valueOf(round(Double.parseDouble(strInsertMonthCountPeopleI)/22)) + "�H�ơC\n";
        messageText += "�C�L��ĳ�ѡG" + String.valueOf(round(Double.parseDouble(strPrintMonthCountTimes)/22)) + " �H���F " + String.valueOf(round(Double.parseDouble(strPrintMonthCountPeople)/22)) + "�H�ơC\n\n";
				*/
        /*
        messageText += "--����@�زz�M--\n";
        messageText += "��ĳ�Ѩϥβv�G" + ((strHomePageCountTimesBank != null) ? strHomePageCountTimesBank : "0") + " �H���F " + strHomePageCountPeopleBank + "�H�ơC";
        if (strHomePageCountName.equals("")) {
            messageText += "�q����L�H�ϥΡr\n";
        }
        else {
            messageText += "�q";
            messageText += strHomePageCountName;
            messageText += " �r\n";
        }
        
        messageText += "�s��ǲΫ��G" + ((strInsertCountTimesTBank != null) ? strInsertCountTimesTBank : "0") + " �H���F " + strInsertCountPeopleTBank + "�H�ơC";
        if (strInsertCountNameT.equals("")) {
            messageText += "�q����L�H�ϥΡr\n";
        }
        else {
            messageText += "�q";
            messageText += strInsertCountNameT;
            messageText += " �r\n";
        }
        
        messageText += "�s���ꫬ�G" + ((strInsertCountTimesIBank != null) ? strInsertCountTimesIBank : "0") + " �H���F " + strInsertCountPeopleIBank + "�H�ơC";
        if (strInsertCountNameI.equals("")) {
            messageText += "�q����L�H�ϥΡr\n";
        }
        else {
            messageText += "�q";
            messageText += strInsertCountNameI;
            messageText += " �r\n";
        }

        messageText += "�C�L��ĳ�ѡG" + strPrintCountTimesBank + " �H���F " + strPrintCountPeopleBank + "�H�ơC";
        if (strPrintCountName.equals("")) {
            messageText += "�q����L�H�ϥΡr\n\n";
        }
        else {
            messageText += "�q";
            messageText += strPrintCountName;
            messageText += " �r\n\n";
        }
        */
        //---<---content------
        if (strContent != null && !strContent.equals("")) {
            messageText += strContent;
        }
        //------content--->---

        boolean sessionDebug = false;
        InternetAddress[] address = null;
        //InternetAddress[] addresses_cc = null;
        //InternetAddress[] addresses_bcc = null;


        try {
            // set properties...
            Properties props = System.getProperties();
	        props.put("mail.host", mailserver);
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
            // new Session service...
	    //javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(props,null);
	    //javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(props, new SMTPAuthenticator(From, "crm"));
	    javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(props, new SMTPAuthenticator(From, "newcrm"));
            mailSession.setDebug(sessionDebug);
	
            // new Message...
            //Message msg = new MimeMessage(mailSession);
            MimeMessage msg = new MimeMessage(mailSession);		//mail server���L�A�D�������u�������s�X���D�� 20090210
            // set sender...
            msg.setFrom(new InternetAddress(From,"��ĳ�Ѩϥβv"));
  
            // set recipient...
            address = InternetAddress.parse(to,false);
            msg.setRecipients(Message.RecipientType.TO, address);
            //address_cc = InternetAddress.parse(cc,false);
            //msg.setRecipients(Message.RecipientType.CC , addresses_cc);
            //address_bcc = InternetAddress.parse(bcc,false);
            //msg.setRecipients(Message.RecipientType.BCC, addresses_bcc);
  
            // set Subject...
            //msg.setSubject(Subject);
			msg.setSubject(Subject,"big5");	//mail server���L�A�D�������u�������s�X���D�� 20090210

            // set SendDate...
            msg.setSentDate(new java.util.Date());
 
            // set Content...
            msg.setText(messageText);
            // msg.setContent(messageText, "text/html; charset=Big5");      // �H HTML �o�e
  
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
    
    private static double round(double in){	//�p���I�H�U���|�ˤ��J popdog 2010/03/30
    	return Math.round(in*100)/100.0;
    }
    
    private static double round( double in , int scale ){//�|�ˤ��J�A�i�ۭq�p�Ʀ�A�ثe���ΡA�]�\���ӥi��|�Ψ�A����g�g popdog 2010/03/30
    	if(scale<0)
    		scale=0;
    	double div = 1.0;
    	for( int i=0;i<scale;i++ ){
    		div *= 10 ;
    	}
    	return Math.round(in*div)/div ;
    }
}
