# fileparsing
The whole service works next way.
When any file downloaded to the Cloud Storage the Cloud Function is triggered.
This function get the file name and the bucket name.
These are used as a parameters of HTTP request for service in this repository which is deployed on the Cloud Run.
This service use file name and bucket name to download this file from Cloud Storage and parse it into the Client entity.
Then required fields are being wrote to one BigQuery table and optional fields to the another one.

Watch this to see the diagram. https://lucid.app/documents/view/6dbd2056-1fb3-48a3-a7cb-e217acdb9864
