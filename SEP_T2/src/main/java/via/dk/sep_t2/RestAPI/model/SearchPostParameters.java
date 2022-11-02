package via.dk.sep_t2.RestAPI.model;

import org.springframework.lang.Nullable;

public class SearchPostParameters
{
    private int creatorId = 0;
    private int cheaperThan = 0;

    public SearchPostParameters( int creatorId, int cheaperThan) {
        this.creatorId = creatorId;
        this.cheaperThan = cheaperThan;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public int getCheaperThan() {
        return cheaperThan;
    }

    public boolean isEmpty()
    {
        return creatorId == 0 && cheaperThan == 0;
    }
}
