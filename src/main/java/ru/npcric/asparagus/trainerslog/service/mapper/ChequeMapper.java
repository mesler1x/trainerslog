package ru.npcric.asparagus.trainerslog.service.mapper;

import org.springframework.stereotype.Component;
import ru.npcric.asparagus.trainerslog.adapter.web.dto.response.cheque.ChequeFullResponse;
import ru.npcric.asparagus.trainerslog.domain.ChequeEntity;

@Component
public class ChequeMapper {
    public ChequeFullResponse entityToFullResponse(ChequeEntity chequeEntity, Integer debt, Integer balance) {
        return new ChequeFullResponse(chequeEntity.getDate(), debt, balance, chequeEntity.getPaymentAmount(),
                chequeEntity.getLink(), chequeEntity.getIsChecked(), chequeEntity.getStudent().getFullName());
    }
}
