errcode-elastic
===============

A complete rewrite of the original [errcode](https://github.com/dimzava/errcode) app where the custom log 
aggregation code has been replaced with [Logstash](https://www.elastic.co/products/logstash) and 
[logstash-forwarder](https://github.com/elastic/logstash-forwarder) and the MySQL backend has been replaced 
with [Elasticsearch](https://www.elastic.co/products/elasticsearch).