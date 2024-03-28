package br.com.desafio.sangiorgio.domain.producers;

import br.com.desafio.sangiorgio.domain.model.Pagamento;
import br.com.desafio.sangiorgio.domain.services.PagamentoService;
import io.awspring.cloud.sns.core.SnsNotification;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PagamentoCreatedNotificationProducer {

    private final String topic;

    private final SnsTemplate snsTemplate;

    public PagamentoCreatedNotificationProducer(@Value("${sns.order.notification-topic}") String topic, SnsTemplate snsTemplate) {
        this.topic = topic;
        this.snsTemplate = snsTemplate;
    }


    @Async
    @EventListener
    public void listen(PagamentoService.PagamentoCreatedEvent event) {

        final var pagamento = event.pagamento();

        log.debug("Trying to send notification about order [{}]", pagamento);
        snsTemplate.sendNotification(topic, SnsNotification.of(pagamento));
        log.info("Notification about order [{}] sent", pagamento);
    }

}
