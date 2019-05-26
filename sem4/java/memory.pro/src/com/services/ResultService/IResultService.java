package com.services.ResultService;

import java.util.Optional;

public interface IResultService {

    Optional<Result> loadResult(int resultId);

}
