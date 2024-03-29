package com.hst.wpay.common;

import com.hst.wpay.user.exception.SigninFailException;
import com.hst.wpay.user.exception.SignupFailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author dlgusrb0808@gmail.com
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler({SigninFailException.class, SignupFailException.class})
	public ResponseEntity<DescribedResponse> handle(ReportableException e) {
		return processReportableException(e);
	}

	@ExceptionHandler(ReportableException.class)
	public ResponseEntity<DescribedResponse> handleReportable(ReportableException e) {
		return processReportableException(e);
	}

	private ResponseEntity<DescribedResponse> processReportableException(ReportableException e) {
		logger.error(e.getLog(), e);
		return ResponseEntity.badRequest().body(createErrorDescribedResponse(e));
	}

	private DescribedResponse createErrorDescribedResponse(ReportableException e) {
		return DescribedResponse.builder()
				.code(e.getDescription().getCode())
				.responseMessage(e.getDescription().getMessage())
				.responseCode(e.getDescription().toString())
				.extraData(e.getExtraData())
				.build();
	}

}
