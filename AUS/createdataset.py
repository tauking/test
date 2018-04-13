from selenium import webdriver
import os,time
from selenium.webdriver.support.select import Select
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
#使用的chrome浏览器
chromedriver = "C:/Users/Administrator/AppData/Local/Google/Chrome/Application/chromedriver.exe"
os.environ["webdriver.chrome.driver"] = chromedriver
wb = webdriver.Chrome(chromedriver)
wb.implicitly_wait(10)
wb.maximize_window()
URL="http://172.16.0.93:8080"

def login(wb,user,passwd):
    wb.find_element_by_xpath("//input[@name='userName']").send_keys(user)
    wb.find_element_by_xpath("//input[@name='passwd']").send_keys(passwd)
    wb.find_element_by_xpath("//button[text()='登录']").click()

def makesuresigledataset(datasetname):
    # 查找系统中是否有同名的数据集，如果有则删除，如果没有则跳过
    wb.find_element_by_xpath("//input[@type='search']").clear()
    wb.find_element_by_xpath("//input[@type='search']").send_keys(datasetname)
    count_tmp = wb.find_elements_by_xpath("//table[@id='datasetList']/tbody/tr")
    if count_tmp[0].find_element_by_xpath("./td[1]").text not in ("表中数据为空","没有匹配结果"):
        for i in count_tmp:
            i.find_element_by_xpath("./td[9]/button[3]").click()

            loc = (By.XPATH, "//div[@id='removeDataset']//div[@class='modal-footer']/button[@class='btn btn-primary']")
            WebDriverWait(wb, 10).until(EC.visibility_of_element_located(loc))
            wb.find_element_by_xpath(
                "//div[@id='removeDataset']//div[@class='modal-footer']/button[@class='btn btn-primary']").click()

def datasetsetting(datasetname,num):
    #loc_create = (By.XPATH, "//button[@name='createDataset']")
    #WebDriverWait(wb, 10).until(EC.element_to_be_clickable(loc_create))
    wb.refresh()
    time.sleep(4)
    wb.find_element_by_xpath("//li[@name='/dataSet']").click()
    wb.find_element_by_xpath("//button[@name='createDataset']").click()
    # 数据集的设置
    wb.find_element_by_xpath("//input[@id='datasetName']").send_keys(datasetname)
    wb.find_element_by_xpath("//input[@id='replication']").clear()
    wb.find_element_by_xpath("//input[@id='replication']").send_keys("0")
    # 创建N个字段
    for i in range(num):
        wb.find_element_by_xpath("//button[@name='createDataset']").click()

def inserdatatodataset(ziduan_list,leixing_list):
    # 依次输入字段信息。支持字段名、描述和类型
    trs = wb.find_elements_by_xpath("//table[@id='schema']/tbody/tr")
    for tr in trs:
        #print(len(tr.find_elements_by_xpath("./td")))
        tr.find_element_by_xpath("./td[2]/div/input").send_keys(ziduan_list[trs.index(tr)])
        tr.find_element_by_xpath("./td[3]/input").send_keys("desc_" + ziduan_list[trs.index(tr)])
        sel = tr.find_element_by_xpath("./td[4]/select")
        Select(sel).select_by_value(leixing_list[trs.index(tr)])

    wb.find_element_by_xpath("//button[@name='saveBtn']").click()

def test_MQL(wb,mql):
    wb.find_element_by_xpath("//textarea[@class='form-control']").send_keys(mql)
    wb.find_element_by_xpath("//button[@name='searchBtn']").click()
    try:
        time.sleep(2)
        errloc=(By.XPATH,"//ul[@id='noty_bottomRight_layout_container']")
        WebDriverWait(wb,5).until(EC.visibility_of_element_located(errloc))
        noty=wb.find_element_by_xpath("//ul[@id='noty_bottomRight_layout_container']")
        notyinfo=noty.find_elements_by_xpath(".//span[@class='noty_text']")
        print("MQL执行报错!!",notyinfo[-1].text,mql)
    except Exception as e:
        print("MQL执行无报错:",mql)

def savereport(name,desc='略'):
        wb.find_element_by_xpath("//button[@name='saveBtn']").click()
        wb.find_element_by_xpath("//input[@id='reportName']").send_keys(name)
        wb.find_element_by_xpath("//textarea[@id='reportDesc']").send_keys(desc)
        wb.find_element_by_xpath("//div[@class='modal-footer']/button[2]").click()
        try:
            time.sleep(2)
            #errloc = (By.XPATH, "//ul[@id='noty_bottomRight_layout_container']")
            #WebDriverWait(wb, 5).until(EC.visibility_of_element_located(errloc))
            noty = wb.find_element_by_xpath("//ul[@id='noty_bottomRight_layout_container']")
            notyinfo = noty.find_elements_by_xpath(".//span[@class='noty_text']")
            print("报表保存时报错：", notyinfo[-1].text, name)
            wb.find_element_by_xpath("//li[@name='/report']").click()
        except Exception as e:
            print("保存成功:", name)



if __name__ == '__main__':
    """
    ziduan_list = ["string_1", "string_2", "string_3", "string_4", "string_5", "string_6", "string_7", "string_8",
                   "string_9", "string_10", "string_11", "string_12", "date_1", "date_2", "date_3", "date_4",
                   "date_5", "date_6", "date_7", "date_8", "date_9", "date_10", "date_11", "date_12", "date_13",
                   "date_14", "date_15", "long_1", "long_2", "long_3", "long_4", "long_5", "long_6", "long_7", "long_8",
                   "int_9", "long_10", "long_11", "int_12", "long_13", "long_14", "long_15", "double_1", "double_2",
                   "double_3", "double_4", "double_5", "double_6", "double_7", "float_8", "double_9", "double_10",
                   "float_11", "double_12", "double_13", "float_14", "double_15", "geo_point_1", "geo_point_2",
                   "geo_point_3", "geo_point_4", "geo_point_5", "geo_point_6", "geo_point_7", "geo_point_8",
                   "geo_point_9", "geo_point_10", "geo_point_11", "geo_point_12", "geo_point_13", "geo_point_14",
                   "geo_point_15"]
    leixing_list = ["string", "string", "string", "string", "string", "string", "string", "string", "string",
                    "string", "string", "string", "date", "date", "date", "date", "date", "date", "date", "date",
                    "date", "date", "date", "date", "date", "date", "date", "long", "long", "long", "long", "long",
                    "long", "long", "long", "long", "long", "long", "long", "long", "long", "long", "double", "double",
                    "double", "double", "double", "double", "double", "double", "double", "double", "double", "double",
                    "double", "double", "double", "geo_point", "geo_point", "geo_point", "geo_point", "geo_point",
                    "geo_point", "geo_point", "geo_point", "geo_point", "geo_point", "geo_point", "geo_point",
                    "geo_point", "geo_point", "geo_point"]
    """
    # 字段名与类型的对应关系列表

    ziduan_list=["intime","Eff","geoPos","src","Lon","Dir","fmt",
                 "GpsSpeed","dataschema","Gpstime","dataattr","Lat",
                 "CarryStatus","PlateID","testInteger","testFloat"]
    leixing_list=["date","long","geo_point","string","double","long",
                  "string","double","string","date","string","double",
                  "long","string","long","double"]

    assert len(ziduan_list)==len(leixing_list)
    datasetname = "taxi"

    wb.get(URL)
    #admin登录
    login(wb,"admin","ausadmin123")
    wb.find_element_by_xpath("//li[@name='/dataSet']").click()
    makesuresigledataset(datasetname)
    datasetsetting(datasetname,len(ziduan_list))


    inserdatatodataset(ziduan_list,leixing_list)

    dataset2="ixat"
    ziduan_list2 = ["name", "id"]
    leixing_list2 = ["string", "string"]
    makesuresigledataset(dataset2)
    datasetsetting(dataset2,len(ziduan_list2))
    inserdatatodataset(ziduan_list2,leixing_list2)
    wb.quit()