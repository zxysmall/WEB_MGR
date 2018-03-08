package string.modular.string.controller;

import string.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import string.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import string.common.persistence.model.Test;
import string.modular.string.service.ITestService;

/**
 * string控制器
 *
 * @author tom
 * @Date 2018-01-19 19:49:42
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

    private String PREFIX = "/string/test/";

    @Autowired
    private ITestService testService;

    /**
     * 跳转到string首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "test.html";
    }

    /**
     * 跳转到添加string
     */
    @RequestMapping("/test_add")
    public String testAdd() {
        return PREFIX + "test_add.html";
    }

    /**
     * 跳转到修改string
     */
    @RequestMapping("/test_update/{testId}")
    public String testUpdate(@PathVariable Integer testId, Model model) {
        Test test = testService.selectById(testId);
        model.addAttribute("item",test);
        LogObjectHolder.me().set(test);
        return PREFIX + "test_edit.html";
    }

    /**
     * 获取string列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return testService.selectList(null);
    }

    /**
     * 新增string
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Test test) {
        testService.insert(test);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除string
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer testId) {
        testService.deleteById(testId);
        return SUCCESS_TIP;
    }

    /**
     * 修改string
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Test test) {
        testService.updateById(test);
        return super.SUCCESS_TIP;
    }

    /**
     * string详情
     */
    @RequestMapping(value = "/detail/{testId}")
    @ResponseBody
    public Object detail(@PathVariable("testId") Integer testId) {
        return testService.selectById(testId);
    }
}
