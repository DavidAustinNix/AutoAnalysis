AutoAnalysis orchestrates auto analysis of sequencing files in GNomEx Experiment using two different daemons running on HCI or on CHPC.

GNomExAutoAnalysis - Sets up primary AutoAnalysis folders for GNomEx Experiment Requests (ER). Run tomatosrvs@hci-deadhorse.hci.utah.edu
1) Interrogates the GNomEx sql server for Align and QC analysis requests from client in the "Bioinformatics" tab of an ER
2) Runs several checks to see if the ER can be serviced: age of the Fastq/ folder files, presence of an md5 checksum file, available organism - library prep matched analysis workflow
3) Creates individual sample job folders with links to the fastq. Adds a RUNME file containing paths to the workflow docs to copy into the job folders and execute on CHPC's redwood cluster.
4) Waits for all of an ER sample job folder contents to be replaced with the analysis results from CHPC and the presence of a COMPLETE file
5) Runs MultiQC on the set of completed job folders for each ER. Runs the USeq JobCleaner to remove .tbi,.crai,.bai and COMPLETE files as well as zip compress any Logs or RunScripts folders. 
6) Send an email alert to the submitter of the ER that the AutoAnalysis is ready.
7) Emails the admin of any issues encountered and a "I'm alive" message every 24hrs.

ChpcAutoAnalysis - Runs and returns AutoAnalysis jobs on the Redwood cluster at CHPC. Run hcipepipeline@redwood2.chpc.utah.edu
1)  Uses ssh to look for job directories at HCI with a RUNME file
2)  Rsyncs the jobs to CHPC using parallele threads with multiple retries
3)  Reads the RUNME and copies over the workflow docs into the job folder
4)  Submits the xxx.sh bash script in each job directory to the slurm redwood cluster.
5)  Checks jobs for COMPLETE, STARTED, QUEUED, FAILED status files
6)  For completed jobs, the daemon deletes the contents of the job run folder on HCI
7)  Rsyncs back the contents of the job to the empty run folder on HCI
8)  Emails the admin of any issues encountered and a "I'm alive" message every 24hrs.

See the files in the ConfigNotes folder for specifics in setting up and running these two daemons (they use the same jar).


