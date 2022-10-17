package com.lotte.danuri.order.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "member")
public interface MemberServiceClient {

}
