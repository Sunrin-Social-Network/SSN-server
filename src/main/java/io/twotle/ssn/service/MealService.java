package io.twotle.ssn.service;

import io.twotle.ssn.component.CustomException;
import io.twotle.ssn.component.ExceptionCode;
import io.twotle.ssn.component.RedisDao;
import io.twotle.ssn.dto.MealDTO;
import io.twotle.ssn.dto.meal.MealServiceDietInfo;
import io.twotle.ssn.dto.meal.Root;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MealService {
    private final RedisDao redisDao;

    @Value("${neisapi.food.url}")
    private String neisUrl;

    @Value("${neisapi.type}")
    private String neisType;

    @Value("${neisapi.office.code}")
    private String neisOfficeCode;

    @Value("${neisapi.school.code}")
    private String neisSchoolCode;

    @Value("${neisapi.key}")
    private String neisKey;


    public MealDTO getTodayMealData() throws CustomException {
        Date from = new Date();
        TimeZone tz = TimeZone.getTimeZone("Asia/Seoul");


        SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
        transFormat.setTimeZone(tz);
        String to = transFormat.format(from);
        String todayMeal = redisDao.getValues(to);
        if(todayMeal == null || todayMeal.equals("")) {
            System.out.println("sadfsd");
            //Root foodData = getData(getFoodReqUrl(to));

            String foodData = getData(getFoodReqUrl(to));
            if(foodData == null) throw new CustomException(ExceptionCode.NO_MEAL_DATA);
            else {
                redisDao.setValues(to,foodData, Duration.ofDays(1));
                todayMeal = foodData;
            }

        }
        return new MealDTO(to, todayMeal);
    }

    private String getFoodReqUrl(String date) {
        System.out.println(neisUrl+"?KEY="+neisKey+"&Type="+neisType+"&ATPT_OFCDC_SC_CODE="+neisOfficeCode+"&SD_SCHUL_CODE="+neisSchoolCode+"&MLSV_YMD="+date);
        return neisUrl+"?KEY="+neisKey+"&Type="+neisType+"&ATPT_OFCDC_SC_CODE="+neisOfficeCode+"&SD_SCHUL_CODE="+neisSchoolCode+"&MLSV_YMD="+date;
    }

    private String getData(String uri) {
        String line = null;
        //Root data = null;
        try {
            URL url = new URL(uri);
            URLConnection conn = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            line = br.readLine();

            JSONParser parser = new JSONParser(line);
            LinkedHashMap<String,Object> o = parser.object();
            List<Object> a = (List<Object>) o.get("mealServiceDietInfo");
            o = (LinkedHashMap<String, Object>) a.get(1);
            a = (List<Object>) o.get("row");
            o = (LinkedHashMap<String, Object>) a.get(0);
            return (String) o.get("DDISH_NM");

        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
        //return data;
    }
}
