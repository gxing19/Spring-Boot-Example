package com.gxitsky.job;

import com.gxitsky.entity.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

/**
 * @Name: PersonItemProcessor
 * @Desc: 批处理中的一个常见范例是获取数据，对其进行转换，然后将其传输到其他位置。
 * 在这里编写一个简单的转换器，将名称转换为大写。
 * @Author: gxing
 * @Date: 2018-09-17 09:42
 **/
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger logger = LogManager.getLogger(PersonItemProcessor.class);

    /**
     * @desc: 不要求输入和输出类型相同。
     * 实际上，在读取一个数据源之后，有时应用程序的数据流需要不同的数据类型。
     * @author: gxing
     * @date: 2018/9/17 10:31
     * @param: [person]
     * @return: com.springboot.springbatch.entity.Person
     **/
    @Override
    public Person process(Person person) throws Exception {

        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();
        final Person transformedPerson = new Person()
                .setFirstName(firstName).setLastName(lastName);
        return transformedPerson;
    }
}