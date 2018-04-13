from createdataset import *

wb.get(URL)
login(wb,"admin","ausadmin123")
time.sleep(5)
wb.find_element_by_xpath("//li[@name='/report']").click()

file = open('F:/sugon/20180330/AutoTestforAUS/AUS/file/mql整理测试taxi.txt',encoding="utf-8")
num=1
for m in file.readlines():
    m=m.strip()
    if m == '':
        continue
    wb.find_element_by_xpath("//button[@name='createReport']").click()
    test_MQL(wb,m)
    savereport("report_mql_test_"+str(num))
    num+=1
    time.sleep(1)

time.sleep(5)
wb.quit()