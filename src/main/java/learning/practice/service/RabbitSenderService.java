package learning.practice.service;

public interface RabbitSenderService {

  boolean sendMessage(String destination, Object payload);
}
