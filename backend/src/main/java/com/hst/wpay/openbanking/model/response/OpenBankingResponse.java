package com.hst.wpay.openbanking.model.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hst.wpay.openbanking.model.OpenBankingConstants;
import lombok.Data;
import lombok.ToString;

/**
 * @author dlgusrb0808@gmail.com
 */
@Data
@ToString
public class OpenBankingResponse {
	@JsonProperty("rsp_code")
	private String rspCode;
	@JsonProperty("rsp_message")
	private String rspMessage;

	public boolean isSuccess() {
		return OpenBankingConstants.RSP_CODE_SUCCESS.equals(this.rspCode);
	}
}
