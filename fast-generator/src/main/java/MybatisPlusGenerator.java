import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

public class MybatisPlusGenerator {


    public static void main(String[] args) throws InterruptedException, IOException {
        String workspace = "";
        String PACKAGENAME = "";
        String TABLES[] = new String[] {};

        String driverName="";
        String url="";
        String username="";
        String password="";

        Properties properties = new Properties();
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("application.properties");
            workspace = new String(properties.getProperty("workspace").getBytes("iso-8859-1"), "gbk");
            String tables = new String(properties.getProperty("tables").getBytes("iso-8859-1"), "gbk");
            PACKAGENAME = new String(properties.getProperty("packageName").getBytes("iso-8859-1"), "gbk");
            TABLES = tables.split(",");


            driverName = new String(properties.getProperty("driverName").getBytes("iso-8859-1"), "gbk");
            url = new String(properties.getProperty("url").getBytes("iso-8859-1"), "gbk");
            username = new String(properties.getProperty("username").getBytes("iso-8859-1"), "gbk");
            password = new String(properties.getProperty("password").getBytes("iso-8859-1"), "gbk");
        } catch (IOException e) {
            e.printStackTrace();
        }

        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(workspace+ "/src/main/java");
        gc.setFileOverride(true);//是否重写文件
        //gc.setActiveRecord(true);//是否支持AR模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor("HOU");
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        //gc.setMapperName("%sDao");
        gc.setMapperName("I%sMapper");
        gc.setServiceName("I%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        /*dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                return super.processTypeConvert(fieldType);
            }
        });*/

        dsc.setDriverName(driverName);
        dsc.setUrl(url);
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        /*是否使用lombok*/
        strategy.setEntityLombokModel(true);
        //strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        //strategy.setTablePrefix(new String[]{"act_"});// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(TABLES); // 需要生成的表
        strategy.setRestControllerStyle(true);//生成restful的controller
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setDbColumnUnderline(true);
        strategy.setSuperEntityClass("cn.fast.web.base.BaseEntity");
        strategy.setSuperControllerClass("cn.fast.web.base.BaseController");
        //父类中的字段
        strategy.setSuperEntityColumns(new String[]{"id", "create_time", "update_time", "create_by", "update_by", "is_del"});
        strategy.setControllerMappingHyphenStyle(true);
        //strategy.setExclude(new String[]{"order"}); // 排除生成的表
        strategy.setEntityBuilderModel(true);
        strategy.setLogicDeleteFieldName("is_del");//逻辑删除字段名称
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setController("controller");
        pc.setParent(PACKAGENAME);
        //pc.setModuleName("");
        mpg.setPackageInfo(pc);
        String packageName=PACKAGENAME;
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                map.put("parent", packageName);
                this.setMap(map);
            }
        };
        mpg.setCfg(cfg);

        // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
        // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：

        TemplateConfig tc = new TemplateConfig();
        tc.setController("/templates/controller.java.vm");
        tc.setEntity("templates/entity.java.vm");
        tc.setService("templates/service.java.vm");
        tc.setMapper("templates/mapper.java.vm");
        tc.setServiceImpl("templates/serviceImpl.java.vm");
        mpg.setTemplate(tc);
        // 执行生成
        mpg.execute();
        // 打印注入属性
        System.err.println(mpg.getCfg().getMap().get("abc"));
        genJpaCode(PACKAGENAME,workspace,TABLES);
        System.exit(0);
    }

    /**
     * 创建文件
     * @param file
     */
    public static void createFilePath(File file){
        if(!file.exists()){
            System.out.println("创建["+file.getAbsolutePath()+"]情况："+file.mkdirs());
        }else{
            System.out.println("存在目录："+file.getAbsolutePath());
        }
    }


    public static void genJpaCode(String packageName,String workspace,String[] tables) throws IOException {
        String jpaRepositormVm = "templates/repository.java.vm";
        Properties p = new Properties();
        p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        p.setProperty("file.resource.loader.path", "");
        p.setProperty("ISO-8859-1", ConstVal.UTF8);
        p.setProperty("input.encoding", ConstVal.UTF8);
        p.setProperty("output.encoding", ConstVal.UTF8);
        p.setProperty("file.resource.loader.unicode", "true");
        VelocityEngine velocityEngine = new VelocityEngine(p);
        for(String s : tables){
            String entityName = upperTable(s);
            Template template = velocityEngine.getTemplate(jpaRepositormVm,ConstVal.UTF8);
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("repositoryName", entityName+"Repository");
            velocityContext.put("repositoryPackage", packageName+".repository");
            velocityContext.put("entity", entityName);
            velocityContext.put("entityPackage", packageName+".entity");
            velocityContext.put("author", "houqj");
            velocityContext.put("date", "2020-01-03");
            StringWriter stringWriter = new StringWriter();
            template.merge(velocityContext, stringWriter);
            String path = workspace + "/src/main/java/"+packageName.replace(".","//")+"//repository"+"//";
            File filePath = new File(path);
            createFilePath(filePath);
            String fileName = path +entityName+"Repository.java";
            File file = new File(fileName);
            FileWriter fw = new FileWriter(file);
            fw.write(stringWriter.toString());
            fw.flush();
            fw.close();
        }
    }
    public static String upperTable(String str)
    {
        // 字符串缓冲区
        StringBuffer sbf = new StringBuffer();
        // 如果字符串包含 下划线
        if (str.contains("_"))
        {
            // 按下划线来切割字符串为数组
            String[] split = str.split("_");
            // 循环数组操作其中的字符串
            for (int i = 0, index = split.length; i < index; i++)
            {
                // 递归调用本方法
                String upperTable = upperTable(split[i]);
                // 添加到字符串缓冲区
                sbf.append(upperTable);
            }
        } else
        {// 字符串不包含下划线
            // 转换成字符数组
            char[] ch = str.toCharArray();
            // 判断首字母是否是字母
            if (ch[0] >= 'a' && ch[0] <= 'z')
            {
                // 利用ASCII码实现大写
                ch[0] = (char) (ch[0] - 32);
            }
            // 添加进字符串缓存区
            sbf.append(ch);
        }
        // 返回
        return sbf.toString();
    }
//————————————————
//    版权声明：本文为CSDN博主「幻凡ss」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/q2158798/article/details/80531437
}
