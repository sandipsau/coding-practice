Change Description (For Everyone)
Level 1 - Current level
You are the proud owner of an amazing and one of the top 10 Job-Boards in the US
Your Job-Board have 1 Million unique users daily which generate between 1-100 Million views a day, a true marvel. [A view is when a user request and see a job in your website]

Every night you get a new List of job objects with 1 Million Jobs as an input to your system. You have the entire night to process this list and prepare those jobs for the next working day, so your site visitors can scan, query and view those jobs.

The jobs in the list are ordered by VIEW desc.
The order of the jobs is important and should be an important consideration in the development.
Jobs with higher views should appear before jobs with less views.
You should keep track of views and change jobs order if views change.
As the owner of the site and it’s sole developer you have created several requirements to handle the high traffic of your website.

Job Object schema on the daily input

{
      "id":"string (unique)",
      "title":"string",
      "description":"string",
      "views":"int"
}
Daily input example:

[{
      "id":"string (unique)",
      "title":"string",
      "description":"string",
      "views":"int"
   },{
    "id": "6586824289e5f5797ad3767f",
    "title": "reprehenderit mollit eiusmod occaecat ea",
    "description": "eu irure non culpa est ea elit consequat sit amet",
    "views": 89842356
  },{
    "id": "658682422be481a909f9a7ad",
    "title": "aute voluptate est sunt aliqua",
    "description": "id ad Lorem sunt non duis et elit excepteur amet",
    "views": 7864576
  },{
    "id": "6586824270e36e806366c3ad",
    "title": "fugiat laborum id laboris est",
    "description": "commodo officia aliquip magna irure laboris Lorem officia nostrud ad",
    "views": 3394135
  },
...
]
Question Level 1:
Implement the following interface:
Explain your solution and write a working code

Class JobBoard {}

JobBoard::ingest_jobs() -> None
	Performance: no requirements
	Details: get a list of jobs object and ingest them into your class

JobBoard::get_next_job(job_id: string) → job_id
	Performance: O(1)
	Details: The function gets a job_id and returns the job_id of the next job in the jobs list. If job_id is the last in the list job list return job_id

JobBoard::get_previous_job(job_id: string) →job_id
	Performance: O(1)
	Details: The function gets a job_id and returns the job_id of the previous job in the jobs list. If job_id is the first job in the list job list return job_id