package string.modular.string.service.impl;

import string.common.persistence.model.Test;
import string.common.persistence.dao.TestMapper;
import string.modular.string.service.ITestService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author string123
 * @since 2018-01-19
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
