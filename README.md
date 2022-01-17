# Getting Started



# test
1. starting infra
2. starting service
3. calling browser reactive: http://localhost:8456/test-call
4. calling browser non-reactive: http://localhost:8456/test-call-2

# infra
using standard eureka with properties
```yaml
server:
  port: 8761

spring:
  application:
    name: eureka-service

eureka:
  client:
    registerWithEureka: false
    fetchRegistry: false
    service-url:
      defaultZone: http://localhost:8761/eureka
  instance:
    prefer-ip-address: true
```