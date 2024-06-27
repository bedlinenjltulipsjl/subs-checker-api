package dev.guarmo.jwttokenserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.guarmo.jwttokenserver.model.invoice.Invoice;
import dev.guarmo.jwttokenserver.model.invoice.dto.GetInvoiceDto;
import dev.guarmo.jwttokenserver.model.invoice.mapper.InvoiceMapper;
import dev.guarmo.jwttokenserver.repository.InvoiceRepository;
import dev.guarmo.jwttokenserver.teleg.TelegramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceGeneratorService {

    private final InvoiceMapper invoiceMapper;
    private final InvoiceRepository invoiceRepository;
    private final TelegramService telegramService;
    private final WestWalletService westWalletService;


    public GetInvoiceDto generateInvoicePageObject(String userLogin, String cryptoCurrencyType, Double topUpAmount, String email) {
        String string = westWalletService.generateInvoicePagePostRequestResponse(userLogin, cryptoCurrencyType, topUpAmount);
        try {
            Invoice invoice = invoiceMapper.getInvoiceFromJson(string);
            invoice.setLabel(userLogin);
            invoice.setEmail(email);
            invoice = invoiceRepository.save(invoice);

            GetInvoiceDto getDto = invoiceMapper.toGetDto(invoice);
            telegramService.sendNotificationAboutStartingInvoice(getDto);
            return getDto;
        } catch (JsonProcessingException e) {
            log.error("Error while parsing json", e);
            throw new RuntimeException("Error while parsing json", e);
        }
    }
}
