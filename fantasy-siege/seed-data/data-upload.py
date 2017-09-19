import pandas as pd
from sqlalchemy import create_engine

file_list = ['matches','players','teams']
engine = create_engine('postgresql://fsig_dev:fsig_dev_pw@localhost/fantasy_siege_dev')

for file in file_list:
    df = pd.read_csv('{}.csv'.format(file))
    df.to_sql(file,engine,if_exists='replace')
    print('uploaded {} file'.format(file))
