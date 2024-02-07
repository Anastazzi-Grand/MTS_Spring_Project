package ru.mts.HW6.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import ru.mts.HW6.service.CreateAnimalService;

public class CreateAnimalBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CreateAnimalService){
            ((CreateAnimalService) bean).setAnimalType();
        }
        return bean;
    }
}
