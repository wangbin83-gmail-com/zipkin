/*
 * Copyright 2012 Twitter Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.twitter.zipkin.builder.Scribe
import com.twitter.zipkin.hbase
import com.twitter.zipkin.collector.builder.CollectorServiceBuilder
import com.twitter.zipkin.storage.Store
import org.apache.hadoop.hbase.{HConstants, HBaseConfiguration}

val storageHost = Option(System.getProperty("zipkin.collector.storage.host", "10.0.2.122"))
val storagePort = Option(System.getProperty("zipkin.collector.storage.port", "2181"))

val conf = HBaseConfiguration.create()
conf.set(HConstants.ZOOKEEPER_QUORUM, storageHost.get)
conf.set(HConstants.ZOOKEEPER_CLIENT_PORT, storagePort.get)

val hbaseBuilder = Store.Builder(
  hbase.StorageBuilder(confOption = Some(conf)),
  hbase.IndexBuilder(confOption = Some(conf)),
  hbase.AggregatesBuilder(confOption = Some(conf))
)

CollectorServiceBuilder(Scribe.Interface(categories = Set("zipkin")))
  .writeTo(hbaseBuilder)
