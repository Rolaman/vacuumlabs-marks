# Trademark API service

## Description

It is a service to store and search for word trademarks.

## Endpoints

### ```/api/trademarks```
Returns 200 OK and trademark view if exists \
Returns 400 NotFound if trademark doesn't exist

#### Params:

- ```name``` - exact name of trademark

#### Response:

```
{
    id: <trademark id>,
    name: <trademark name>
}
```


### ```/api/trademarks/search```
Returns 200 OK and trademark views

#### Params:

- ```text``` - text to search for trademarks

#### Response:

```
{
    item: [
        {
            id: <trademark id>,
            name: <trademark name>
        }
    ]
}
```

## Profiles

### ```Populating```
Enable this profile to populate database by trademarks from directory ```trademark``` 