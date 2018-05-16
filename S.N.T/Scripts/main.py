import threading
from queue import Queue
from spoder import Spider
from domain import *
from general import *

# Need to implement project name and homepage as args, to be sent from java 
PROJECT_NAME = 'foods'
HOMEPAGE = 'http://www.example.co.uk/'
DOMAIN_NAME = get_domain_name(HOMEPAGE)
QUEUE_FILE = PROJECT_NAME + '/queue.txt'
CRAWLED_FILE = PROJECT_NAME + '/crawled.txt'
NUMBER_OF_THREADS = 6
queue = Queue()
Spider(PROJECT_NAME, HOMEPAGE, DOMAIN_NAME)


# create worker threads (will die when main exits)
def create_spiders():
    for _ in range(NUMBER_OF_THREADS):
        th = threading.Thread(target=work)
        th.daemon = True
        th.start()


# WORK U SPODERS - does next job in queue
def work():
    while True:
        url = queue.get()
        Spider.crawl_page(threading.current_thread().name, url)
        queue.task_done()


# each queued link is a new job
def create_jobs():
    for link in file_to_set(QUEUE_FILE):
        queue.put(link)
    queue.join()
    crawl()


# check if items in queue list, if so; crawl
def crawl():
    queued_links = file_to_set(QUEUE_FILE)
    if len(queued_links > 0):
        print(str(len(queued_links)) + ' links in the queue')
        create_jobs()
