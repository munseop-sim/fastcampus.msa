## confluentinc/kafka, bitnami/kafka
- 제공하는 회사가 다름(confluent, bitnami)
  - bitnami: https://bitnami.com/
  - confluent: https://www.confluent.io/
-  `confluentinc/cp-kafka`는 Confluent Platform의 일부로서, Confluent에서 제공하는 다양한 추가 기능과 통합됩니다. 예를 들어, Confluent Control Center, Confluent Schema Registry, Confluent REST Proxy 등과 같은 Confluent Platform의 컴포넌트를 사용하려면 confluentinc/cp-kafka를 사용하는 것이 좋습니다
-  `bitnami/kafka`는 Bitnami의 표준화된 설정 및 배포 메커니즘을 사용합니다. 이는 일관된 사용 경험을 제공하며, Bitnami의 다른 제품과의 통합이 용이합니다.
  - 브로커를 하나만 생성했을때 차이
    - bitnami/kafka : ok
    - confluentinc/cp-kafka : error
      - 메세지를 producer가 produce할 때는 정상작동하나, cosumer가 메세지를 consume할 때 아래와 같은 에러가 발생
      - 브로커를 적어도 3개이상 설정해야 되는것 같음.
      ```
        2024-05-20 15:53:41 [2024-05-20 06:53:41,082] INFO [Admin Manager on Broker 1]: Error processing create topic request CreatableTopic(name='__consumer_offsets', numPartitions=50, replicationFactor=3, assignments=[], configs=[CreateableTopicConfig(name='compression.type', value='producer'), CreateableTopicConfig(name='cleanup.policy', value='compact'), CreateableTopicConfig(name='segment.bytes', value='104857600')]) (kafka.server.ZkAdminManager)
        2024-05-20 15:53:41 org.apache.kafka.common.errors.InvalidReplicationFactorException: Replication factor: 3 larger than available brokers: 1.
        2024-05-20 15:53:41 [2024-05-20 06:53:41,183] INFO [Admin Manager on Broker 1]: Error processing create topic request CreatableTopic(name='__consumer_offsets', numPartitions=50, replicationFactor=3, assignments=[], configs=[CreateableTopicConfig(name='compression.type', value='producer'), CreateableTopicConfig(name='cleanup.policy', value='compact'), CreateableTopicConfig(name='segment.bytes', value='104857600')]) (kafka.server.ZkAdminManager)
        2024-05-20 15:53:41 org.apache.kafka.common.errors.InvalidReplicationFactorException: Replication factor: 3 larger than available brokers: 1.
        2024-05-20 15:53:41 [2024-05-20 06:53:41,283] INFO [Admin Manager on Broker 1]: Error processing create topic request CreatableTopic(name='__consumer_offsets', numPartitions=50, replicationFactor=3, assignments=[], configs=[CreateableTopicConfig(name='compression.type', value='producer'), CreateableTopicConfig(name='cleanup.policy', value='compact'), CreateableTopicConfig(name='segment.bytes', value='104857600')]) (kafka.server.ZkAdminManager)
        2024-05-20 15:53:41 org.apache.kafka.common.errors.InvalidReplicationFactorException: Replication factor: 3 larger than available brokers: 1.
        2024-05-20 15:53:41 [2024-05-20 06:53:41,385] INFO [Admin Manager on Broker 1]: Error processing create topic request CreatableTopic(name='__consumer_offsets', numPartitions=50, replicationFactor=3, assignments=[], configs=[CreateableTopicConfig(name='compression.type', value='producer'), CreateableTopicConfig(name='cleanup.policy', value='compact'), CreateableTopicConfig(name='segment.bytes', value='104857600')]) (kafka.server.ZkAdminManager)
        2024-05-20 15:53:41 org.apache.kafka.common.errors.InvalidReplicationFactorException: Replication factor: 3 larger than available brokers: 1.
        2024-05-20 15:53:41 [2024-05-20 06:53:41,485] INFO [Admin Manager on Broker 1]: Error processing create topic request CreatableTopic(name='__consumer_offsets', numPartitions=50, replicationFactor=3, assignments=[], configs=[CreateableTopicConfig(name='compression.type', value='producer'), CreateableTopicConfig(name='cleanup.policy', value='compact'), CreateableTopicConfig(name='segment.bytes', value='104857600')]) (kafka.server.ZkAdminManager)
        2024-05-20 15:53:41 org.apache.kafka.common.errors.InvalidReplicationFactorException: Replication factor: 3 larger than available brokers: 1.
        2024-05-20 15:53:41 [2024-05-20 06:53:41,585] INFO [Admin Manager on Broker 1]: Error processing create topic request CreatableTopic(name='__consumer_offsets', numPartitions=50, replicationFactor=3, assignments=[], configs=[CreateableTopicConfig(name='compression.type', value='producer'), CreateableTopicConfig(name='cleanup.policy', value='compact'), CreateableTopicConfig(name='segment.bytes', value='104857600')]) (kafka.server.ZkAdminManager)
        2024-05-20 15:53:41 org.apache.kafka.common.errors.InvalidReplicationFactorException: Replication factor: 3 larger than available brokers: 1.
      ```
  - 결론 -> 실습은 `bitnami/kafka`를 사용