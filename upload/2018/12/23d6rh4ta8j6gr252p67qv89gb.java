// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SpmdServiceApplication.java

package com.runnet.cqnw;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.runnet.cqnw.service.common.CommonConfig;
import com.runnet.cqnw.service.config.MyConf;
import com.runnet.cqnw.service.module.db.DBUtil;
import com.runnet.cqnw.service.module.util.Common;
import com.runnet.cqnw.service.task.micaps4.SeaLevel;
import com.runnet.cqnw.service.task.util.DatabaseUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.*;
import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.converter.HttpMessageConverter;

public class SpmdServiceApplication
{

	private static List productList;
	String driver;
	String username;
	String password;
	String url;

	public SpmdServiceApplication()
	{
	}

	public static void main(String args[])
		throws Exception
	{
		ConfigurableApplicationContext context = SpringApplication.run(com/runnet/cqnw/SpmdServiceApplication, args);
		System.out.println(context.getBean(com/runnet/cqnw/service/config/MyConf));
		productList = DatabaseUtil.getProductList("Micaps4EC");
		writeExcel(productList);
	}

	public HttpMessageConverters fastJsonHttpMessageConverters()
	{
		FastJsonHttpMessageConverter fastConvert = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(new SerializerFeature[] {
			SerializerFeature.PrettyFormat
		});
		fastConvert.setFastJsonConfig(fastJsonConfig);
		return new HttpMessageConverters(new HttpMessageConverter[] {
			fastConvert
		});
	}

	public DataSource dataSource()
	{
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(driver);
		hikariConfig.setJdbcUrl(url);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		hikariConfig.setMaximumPoolSize(5);
		hikariConfig.setConnectionTestQuery("SELECT 1");
		hikariConfig.setPoolName("springHikariCP");
		hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
		hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		DBUtil.set("default", driver, url, username, password);
		return dataSource;
	}

	public CommonConfig commonConfig()
	{
		CommonConfig commonConfig = new CommonConfig();
		return commonConfig;
	}

	public static void writeExcel(List prods)
	{
		List rows = new ArrayList();
		List commonAging = SeaLevel.getCommonAging();
		for (Iterator iterator = prods.iterator(); iterator.hasNext();)
		{
			Map map = (Map)iterator.next();
			String t;
			String fileName;
			String productCode;
			String productName;
			for (Iterator iterator1 = commonAging.iterator(); iterator1.hasNext(); rows.add(test02(t, fileName, productCode, productName)))
			{
				t = (String)iterator1.next();
				fileName = Common.toString(map.get("productCode"));
				if ("000".equals(t))
					fileName = (new StringBuilder(String.valueOf(fileName))).append("YYYYMMDDHHTTSS.jpg").toString();
				else
					fileName = (new StringBuilder(String.valueOf(fileName))).append("YYYYMMDDHHTTSS_").append(t).append(".jpg").toString();
				productCode = MessageFormat.format("{0}{1}", new Object[] {
					Common.toString(map.get("productCode")), "000".equals(t) ? "" : (new StringBuilder("_")).append(t).toString()
				});
				productName = MessageFormat.format("{0}_{1}{2}", new Object[] {
					"EC", Common.toString(map.get("remark")), "000".equals(t) ? "" : (new StringBuilder("-")).append(Integer.parseInt(t)).append("小时").toString()
				});
			}

		}

		ExcelWriter writer = ExcelUtil.getWriter("F:\\熊鸾\\重庆内网\\新增模板20181203.xls", "报表");
		writer.passCurrentRow();
		writer.write(rows);
		writer.close();
	}

	private static List test02(String time, String fileName, String productCode, String productName)
	{
		List values = new ArrayList();
		time = (new StringBuilder("_")).append(time).toString();
		int y = fileName.indexOf("YYYY");
		String fo = MessageFormat.format("Y:4:{0}_M:2:{1}_D:2:{2}_H:2:{3}_T:2:{4}_S:2:{4}", new Object[] {
			Integer.valueOf(y), Integer.valueOf(y + 4), Integer.valueOf(y + 6), Integer.valueOf(y + 8), Integer.valueOf(y + 10)
		});
		values.add("气象观测");
		values.add("地面观测");
		values.add("重庆范围等值面");
		values.add(productCode);
		values.add(productName);
		values.add("重庆本地");
		values.add("气象信息与技术保障中心");
		values.add("");
		values.add("");
		values.add("");
		values.add("CTS");
		values.add("日值");
		values.add("");
		values.add("2");
		values.add("file_url");
		values.add("contour");
		values.add("com.actionsky.cimiss.decoder.unstructure.UnstructureDecoder");
		values.add("FTP类型");
		values.add("/datas/cts/unstructure/cont_web");
		values.add((new StringBuilder("fileName:")).append(fileName.replace("YYYYMMDDHHTTSS", "20[0-9]{10}00")).toString());
		values.add("/nas/SURF/CONT/PRE");
		values.add("yyyy/yyyyMMdd");
		values.add("北京时间");
		values.add("0");
		values.add("jpg");
		values.add(fileName);
		values.add("是");
		values.add(fo);
		values.add("北京时间");
		return values;
	}
}
