package com.william.collegeapartmentsbacke.service.impl;

import cn.hutool.json.JSONObject;
import com.william.collegeapartmentsbacke.common.utils.HttpClientUtil;
import com.william.collegeapartmentsbacke.service.PuInfoService;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class PuInfoServiceImpl implements PuInfoService {
    private static final String LoginURL= "https://apis.pocketuni.net/uc/user/login";
    private static final String InfoURL="https://apis.pocketuni.net/apis/user/pc-info";
    private static final int TIMEOUT = 3000;
    private static final long  SCHOOL_ID=208754666766336L;
    private Map<String, Object> param=new LinkedHashMap<>();

    @Override
    public String Login(String username, String password) throws Exception{
        param.put("userName",username);
        param.put("password",password);
        param.put("sid",SCHOOL_ID);
        param.put("device","pc");
        String result=HttpClientUtil.doPost4Json(LoginURL,param);
        return result;
    }

    @Override
    public String getPuInfo(String username, String password) throws Exception {
        String result=this.Login(username,password);
        JSONObject jsonObject=new JSONObject(result);
        JSONObject data=jsonObject.getJSONObject("data");
        String AuthHeader= "Bear %s:%s".formatted(data.getStr("token"), SCHOOL_ID);
        return HttpClientUtil.doPost(InfoURL,null,AuthHeader);
    }
    //    @Override
//    public String  getSchoolList() throws Exception {
//        URL url=new URL(URL);
//        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//        connection.setConnectTimeout(TIMEOUT);
//        if (connection.getResponseCode()==200){
//            BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String line;
//            StringBuilder result=new StringBuilder();
//            while ((line=reader.readLine())!=null){
//                result.append(line);
//            }
//            reader.close();
//            return result.toString();
//        }
//        else
//            return "error";
//    }
//
//    @Override
//    public String Get_sid()
//    {
//        try {
//            List<String> schoolList= Collections.singletonList(this.getSchoolList());//获取学校列表;
//            System.out.println(this.getSchoolList());
//            System.out.println(schoolList);
//            List<String> match=schoolList.stream().filter(s->s.contains(SCHOOL_ID)).toList();
//            match.forEach(System.out::println);
//            return match.get(0);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public String FindSchool(List<String> schoolList, String schoolName) {
//        return "";
//    }
}
