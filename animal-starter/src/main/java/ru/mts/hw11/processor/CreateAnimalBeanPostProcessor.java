package ru.mts.hw11.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import ru.mts.hw11.service.CreateAnimalService;

public class CreateAnimalBeanPostProcessor implements BeanPostProcessor {
    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CreateAnimalService){
            ((CreateAnimalService) bean).setAnimalType();
            System.out.println("set animal type");
        }
        return bean;
    }
}
