package dataaccess;

import domain.Site;

import java.util.List;

public interface SiteRepo extends BaseRepo<Site, Long>{

    List<Site> findSitesByOrt(String ort);
    List<Site> findSitesByName(String ort);
    List<Site> findAcitveSites();

}
