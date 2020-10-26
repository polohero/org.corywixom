# org.corywixom.cache
The purpose of this service is to play around creating distributed cache service. The service currently utilizes DynamoDB as a backing store, (TBD is to switch to use the DAX for DynamoDB for in memory caching).

The theory is that I can create a global DynamoDB table, backed by DAX, that will allow the service to handle a multi region-caching solution. TBD to add deployment steps and more documentation.
