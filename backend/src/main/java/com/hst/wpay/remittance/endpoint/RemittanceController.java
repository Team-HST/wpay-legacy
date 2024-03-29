package com.hst.wpay.remittance.endpoint;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hst.wpay.remittance.model.request.TransferMoneyRequest;
import com.hst.wpay.remittance.model.request.WeddingRemittanceRequest;
import com.hst.wpay.remittance.model.response.RemittanceResponse;
import com.hst.wpay.remittance.service.RemittanceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author dlgusrb0808@gmail.com
 */
@RestController
@RequestMapping("remittance")
@Api( tags = "2. 축의금 API", description = "축의금 송금, 내역조회 기능 전반을 제공합니다.")
public class RemittanceController {

	@Autowired
	private RemittanceService remittanceService;

	@ApiOperation(value = "축의금 송금", notes = "축의금을 송금합니다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공")
	})
	@PostMapping("transfer")
	public ResponseEntity<String> transferMoney(@RequestBody TransferMoneyRequest request) {
		remittanceService.transferMoney(request);
		return ResponseEntity.ok("송금이 완료되었습니다.");
	}

	@ApiOperation(value = "축의금 지출내역 조회", notes = "사용자의 축의금 지출내역을 제공합니다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공")
	})
	@GetMapping("user/{userSeq}/histories/{month}")
	public ResponseEntity<List<RemittanceResponse>> getUserRemittanceHistories(@PathVariable Long userSeq, @PathVariable Integer month) {
		return ResponseEntity.ok(remittanceService.getUserRemittanceHistories(userSeq, month));
	}
	
	@ApiOperation(value = "축의금 내역 조회", notes = "정산이 완료된 시점의 혼주 축의금 내역을 제공합니다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공")
	})
	@PostMapping("receive")
	public ResponseEntity<List<RemittanceResponse>> getWeddingRemittanceHistories(@RequestBody WeddingRemittanceRequest weddingRemittanceRequest) {
		return ResponseEntity.ok(remittanceService.getWeddingRemittanceHistories(weddingRemittanceRequest));
	}


}
