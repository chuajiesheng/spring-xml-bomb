# Vulnerability of Spring to XML Bomb

Referencing CVE-2015-3192:
1. [Pivotal CVE](http://pivotal.io/security/cve-2015-3192)
2. [SourceClear CVE](https://srcclr.com/security/denial-service-dos-xml-bomb/java/s-1799)
3. [Spring Bug Report](https://jira.spring.io/browse/SPR-13136)

Objective of this project:
1. Determine the vulnerable methods causing this bug
2. Proof of concept of the vulnerability

Plan:
1. Have a simple hello world Spring application
2. Accept XML payload
3. Send XML bomb
4. Demonstrate vulnerability

## Steps
1. Run the sample app via `mvn jetty:run`
2. Upgrade sample code to use *3.2.0.RELEASE* which is one of the vulnerable version

## Reference
1. [Spring sample app](https://github.com/earldouglas/spring-mvc-json-and-xml)