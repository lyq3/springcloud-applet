package com.lyq3.applet.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class Generator {
    //代码输出路径
    private static String OUTPUTDIR = "C:\\Users\\Administrator\\Desktop\\code";
    //作者
    private static String AUTHOR = "卡卢比";
    //包名
    private static String PACKAGENAME = "com.lyq3.applet.sso.server.entity";


    public static void main(String[] arg) {
        String[] tableNames = {"user"};//需要生成代码的表名
        generateByTables(PACKAGENAME, tableNames);
    }


    private static void generateByTables(String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://112.74.57.197:3333/applet";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setUrl(dbUrl);
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("mysql_92932266");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true);
//        strategyConfig  .setEntityLombokModel(false);
        strategyConfig.setDbColumnUnderline(true);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setInclude(tableNames);
        strategyConfig.setEntityBuilderModel(true);
        config.setActiveRecord(true);
        config.setAuthor(AUTHOR);
        config.setOutputDir(OUTPUTDIR);
        //每次生成后覆盖
        config.setFileOverride(true);
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(config);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setStrategy(strategyConfig);
        //包设置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(packageName);
        packageConfig.setEntity("po");
        //模板设置
        TemplateConfig tc = new TemplateConfig();
        //以下模块不生成
//        tc.setController(null);
//        tc.setXml(null);
//        tc.setService(null);
//        tc.setServiceImpl(null);
        autoGenerator.setPackageInfo(packageConfig);
        autoGenerator.setTemplate(tc);
        autoGenerator.execute();
    }
}